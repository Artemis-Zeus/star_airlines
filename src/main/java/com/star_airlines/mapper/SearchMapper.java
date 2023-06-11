package com.star_airlines.mapper;

import com.star_airlines.pojo.Car;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SearchMapper {
    List<Flights> search(String depart, String arrival, LocalDateTime date, Integer type,LocalDate tomorrow);

    List<Hotel> searchHotel(String arrival);

    List<Car> searchCar(String arrival);

//    检测是否有重复航班
    Record searchRecord(Integer flightId,Integer userId);

    Integer getFlightPrice(Integer id);

//    获取订单记录
    Record getRecord(String id);

    Integer getHotelPrice(Integer hotelId);

    Integer getCarPrice(Integer carId);

    List<String> searchAddress();
}
