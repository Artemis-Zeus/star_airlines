package com.star_airlines.service;
import com.star_airlines.pojo.Car;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchService {
    List<Flights> searchFlight(String depart, String arrival, LocalDate date, Integer type);

    List<Hotel> searchHotel(String arrival);

    List<Car> searchCar(String arrival);

    void book_ticket(Record record);

    Record searchRecord(String id,Integer userId);

    void update_status(String id, LocalDateTime now);

    void refund(String id);
}
