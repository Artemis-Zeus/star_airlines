package com.star_airlines.controller;

import com.star_airlines.pojo.Car;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Hotel;
import com.star_airlines.pojo.Result;
import com.star_airlines.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;

    //    查询航班
    @GetMapping("/flight")
    public Result get_flights(String depart, String arrival, LocalDate date, Integer type) {
        log.info(" 查询航班");
        if (date == null)
            date = LocalDate.now();
        List<Flights> flights = searchService.searchFlight(depart, arrival, date, type);
        return Result.success(flights);
    }

    //    查询酒店
    @GetMapping("/hotel")
    public Result get_hotel(String arrival) {
        log.info("查询酒店");
        List<Hotel> hotel = searchService.searchHotel(arrival);
        return Result.success(hotel);
    }

    //    查询租车
    @GetMapping("/car")
    public Result get_car(String arrival) {
        log.info("查询酒店");
        List<Car> car = searchService.searchCar(arrival);
        return Result.success(car);
    }

    //    查询所有地点
    @GetMapping("/depart")
    public Result get_depart() {
        log.info("search depart");
        List<String> depart = searchService.searchDepart();
        return Result.success(depart);
    }

    @GetMapping("/arrival")
    public Result get_arrival() {
        log.info("search arrival");
        List<String> arrival = searchService.searchArrival();
        return Result.success(arrival);
    }

    @GetMapping("/hotels")
    public Result get_hotel() {
        log.info("search hotel");
        List<Map<String,String>> hotels = searchService.searchAllHotel();
        return Result.success(hotels);
    }
}

