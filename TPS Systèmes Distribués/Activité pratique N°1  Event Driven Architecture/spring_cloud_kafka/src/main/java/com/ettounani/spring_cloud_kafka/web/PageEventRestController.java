package com.ettounani.spring_cloud_kafka.web;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ettounani.spring_cloud_kafka.entities.PageEvent;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class PageEventRestController {

    @Autowired
    private StreamBridge streamBridge;

    // publish method
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publishEvent(@PathVariable String topic, @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name, "user_" + new Random().nextInt(100), new Date(),
                10 + new Random().nextInt(100));
        streamBridge.send(
                topic,
                pageEvent);
        return pageEvent;
    }

     @GetMapping(value = "/analytics",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String,Long>> analytics(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq->{
                    Map<String,Long> map=new HashMap<>();
                    ReadOnlyKeyValueStore<String, Long> stats = interactiveQueryService.getQueryableStore("count-store", QueryableStoreTypes.keyValueStore());
                    Instant now=Instant.now();
                    Instant from=now.minusSeconds(5);
                    KeyValueIterator<String, Long> keyValueIterator = stats.all();
                    while (keyValueIterator.hasNext()){
                        KeyValue<String, Long> next = keyValueIterator.next();
                        map.put(next.key,next.value);
                    }
                    return map;
                });
    }
    @GetMapping(value = "/analyticsWindows",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String,Long>> analyticsWindows(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq->{
                    Map<String,Long> map=new HashMap<>();
                    ReadOnlyWindowStore<String, Long> stats = interactiveQueryService.getQueryableStore("count-store", QueryableStoreTypes.windowStore());
                    Instant now=Instant.now();
                    Instant from=now.minusSeconds(30);
                    KeyValueIterator<Windowed<String>, Long> windowedLongKeyValueIterator = stats.fetchAll(from, now);
                    while (windowedLongKeyValueIterator.hasNext()){
                        KeyValue<Windowed<String>, Long> next = windowedLongKeyValueIterator.next();
                        map.put(next.key.key(),next.value);
                    }
                    return map;
                });
    }
    @GetMapping(value = "/analyticsAggregate",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String,Double>> analyticsAggregate(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq->{
                    Map<String,Double> map=new HashMap<>();
                    ReadOnlyWindowStore<String, Double> stats = interactiveQueryService.getQueryableStore("total-store", QueryableStoreTypes.windowStore());
                    Instant now=Instant.now();
                    Instant from=now.minusSeconds(30);
                    KeyValueIterator<Windowed<String>, Double> windowedLongKeyValueIterator = stats.fetchAll(from, now);
                    while (windowedLongKeyValueIterator.hasNext()){
                        KeyValue<Windowed<String>, Double> next = windowedLongKeyValueIterator.next();
                        map.put(next.key.key(),next.value);
                    }
                    return map;
                });
    }

}
