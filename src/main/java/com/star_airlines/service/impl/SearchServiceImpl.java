package com.star_airlines.service.impl;

import com.star_airlines.mapper.SearchMapper;
import com.star_airlines.pojo.Car;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Record;
import com.star_airlines.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Override
    public List<Flights> searchFlight(Flights flight) {
        return searchMapper.search(flight);
    }

    @Override
    public List<Hotel> searchHotel(String arrival) {
        return searchMapper.searchHotel(arrival);
    }

    @Override
    public List<Car> searchCar(String arrival) {
        return searchMapper.searchCar(arrival);
    }

    @Override
    public void book_ticket(Record record) {
        searchMapper.boot_ticket(record);
    }

    @Override
    public Record searchRecord(String id,Integer userId) {
        return searchMapper.searchRecord(id,userId);
    }

    @Override
    public void update_status(String id, LocalDateTime now) {
        searchMapper.update(id,now);
    }

    @Override
    public void refund(String id) {
        searchMapper.refund(id);
    }
}
