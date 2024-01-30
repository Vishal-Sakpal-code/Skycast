package com.spring.project.skycast.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Wind {

    private double speed;
    private String direction;

    public Wind(double speed, String direction) {
        this.speed = speed;
        this.direction = direction;
    }
}
