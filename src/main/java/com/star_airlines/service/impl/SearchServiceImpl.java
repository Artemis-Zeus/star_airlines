package com.star_airlines.service.impl;

import com.star_airlines.mapper.SearchMapper;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Record;
import com.star_airlines.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Override
    public List<Flights> searchFlight(String depart, String arrival, LocalDate date, Integer type) {
        LocalDate tomorrow = date.plusDays(1);
        LocalDateTime localDateTime = date.atStartOfDay();
        LocalDateTime now=LocalDateTime.now();
        if (now.isAfter(localDateTime)){
            localDateTime=now;
        }
        return searchMapper.search(depart, arrival, localDateTime, type, tomorrow);
    }

    @Override
    public Flights getFlight(Integer id) {
        return searchMapper.getFlight(id);
    }

    @Override
    public List<Hotel> searchHotel(String arrival) {
        return searchMapper.searchHotel(arrival);
    }

    @Override
    public Hotel getHotel(Integer id) {
        return searchMapper.getHotel(id);
    }


    @Override
    public Record searchRecord(Integer flightId, Integer userId) {
        return searchMapper.searchRecord(flightId, userId);
    }

    @Override
    public Integer getFlightPrice(Integer id) {
        return searchMapper.getFlightPrice(id);
    }

    @Override
    public Record getRecord(String id) {
        return searchMapper.getRecord(id);
    }

    @Override
    public Integer getHotelPrice(Integer hotelId) {
        return searchMapper.getHotelPrice(hotelId);
    }


    @Override
    public List<String> searchDepart() {
        return searchMapper.searchDepart();
    }

    @Override
    public List<String> searchArrival() {
        return searchMapper.searchArrival();
    }

    @Override
    public List<Map<String,String>> searchAllHotel() {
        return searchMapper.searchAllHotel();
    }

}
