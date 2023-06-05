package com.star_airlines.controller;

import com.star_airlines.pojo.*;
import com.star_airlines.pojo.Record;
import com.star_airlines.service.SearchService;
import com.star_airlines.service.UserService;
import com.star_airlines.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserService userService;
//    查询航班
    @GetMapping("/flight")
    public Result get_flights(@RequestBody Flights flight){
        log.info(" 查询航班");
        List<Flights> flights= searchService.searchFlight(flight);
        return Result.success(flights);
    }
//    查询酒店
    @GetMapping("/hotel")
    public Result get_hotel(@RequestBody Flights flight){
        log.info("查询酒店");
        List<Hotel> hotel= searchService.searchHotel(flight.getArrival());
        return Result.success(hotel);
    }

//    查询租车
    @GetMapping("/car")
    public Result get_car(@RequestBody Flights flight) {
        log.info("查询酒店");
        List<Car> car = searchService.searchCar(flight.getArrival());
        return Result.success(car);
    }
}

