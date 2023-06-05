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
    List<Flights> search(String depart, String arrival, LocalDate date, Integer type,LocalDate tomorrow);

    List<Hotel> searchHotel(String arrival);

    List<Car> searchCar(String arrival);



    Record searchRecord(Integer id,Integer userId);




    Integer getFlightPrice(Integer id);

    Record getRecord(String id);

    Integer getHotelPrice(Integer hotelId);

    Integer getCarPrice(Integer carId);
}
