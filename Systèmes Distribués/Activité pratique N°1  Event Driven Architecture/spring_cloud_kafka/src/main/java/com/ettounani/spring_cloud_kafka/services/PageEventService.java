package com.ettounani.spring_cloud_kafka.services;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ettounani.spring_cloud_kafka.entities.PageEvent;

@Service
public class PageEventService {

    // Consumer
    @Bean
    public Consumer<PageEvent> pageEventConsumer() {
        return (input) -> {
            System.out.println("***************************");
            System.out.println(input.toString());
            System.out.println("***************************");
        };
    }

    // Supplier
    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> new PageEvent("Abo_" + new Random().nextInt(100),
                "user_" + new Random().nextInt(100),
                new Date(), 10 + new Random().nextInt(100));
    }

    // Function
    @Bean
    public Function<PageEvent, PageEvent> pageEventFunction() {
        return (input) -> {
            input.setDuration(input.getDuration() * 2);
            input.setName(input.getName().toUpperCase());
            input.setUser(input.getUser().toUpperCase());
            return input;
        };
    }
    // Function
    @Bean
    public Function<KStream<String,PageEvent>, KStream<String,Long>> kStreamFunction() {
        return (input) -> input
                .filter((k,v) -> v.getDuration() > 20)
                .map((k,v) -> new KeyValue<>(v.getName(), 0L))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                .windowedBy(TimeWindows.of(Duration.ofSeconds(5000)))
                .count()
                .toStream()
                .map((k,v) -> new KeyValue<>("=>"+k.window().startTime()+k.window().endTime()+k.key(), v));
    }

}
