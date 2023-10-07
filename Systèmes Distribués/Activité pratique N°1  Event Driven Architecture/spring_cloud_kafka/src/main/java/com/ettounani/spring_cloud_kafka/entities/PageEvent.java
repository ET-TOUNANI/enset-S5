package com.ettounani.spring_cloud_kafka.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageEvent {
    private String name;
    private String user;
    private Date date;
    private long duration;

}