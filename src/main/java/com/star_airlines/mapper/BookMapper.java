package com.star_airlines.mapper;

import com.star_airlines.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface BookMapper {
    void boot_ticket(Record record);

    void refund(String id, LocalDateTime now);
}
