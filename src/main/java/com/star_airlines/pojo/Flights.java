package com.star_airlines.pojo;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    private Integer price;
    private Integer type;
    private String name;
}
