package com.star_airlines.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private String id;
    private Integer userId;
    private Integer flightId;
    private Integer hotelId;
    private Integer days;
    private Integer carId;
    private Integer price;
    private Integer type;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer usePoint=0;
}
