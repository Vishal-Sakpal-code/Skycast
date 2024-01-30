package com.spring.project.skycast.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private double lat;
    private double lon;
    private String name;
    private String type;
}
