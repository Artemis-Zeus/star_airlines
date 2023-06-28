package com.star_airlines.mapper;

import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper {
    List<Flights> search(String depart, String arrival, LocalDateTime date, Integer type,LocalDate tomorrow);

    List<Hotel> searchHotel(String arrival);

//    检测是否有重复航班
    Record searchRecord(Integer flightId,Integer userId);

    Integer getFlightPrice(Integer id);

//    获取订单记录
    Record getRecord(String id);

    Integer getHotelPrice(Integer hotelId);


    List<String> searchDepart();

    List<String> searchArrival();

    @SuppressWarnings("MybatisXMapperMethodInspection")
    List<Map<String,String>> searchAllHotel();
}
