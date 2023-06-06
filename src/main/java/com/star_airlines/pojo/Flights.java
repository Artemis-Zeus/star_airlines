package com.star_airlines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flights {
    private Integer id;
    private String depart;
    private String arrival;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private Integer price;
    private Integer type;
    private String name;
}
