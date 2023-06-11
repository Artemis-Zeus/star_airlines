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
import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/address")
    public Result get_address() {
        log.info("search address");
        List<String> address = searchService.searchAddress();
        List<String> address1 = new ArrayList<>();
        List<String> address2 = new ArrayList<>();
        for (int i = 0; i < address.size(); i++) {
            if (i % 2 == 0) {
                address1.add(address.get(i));
            } else address2.add(address.get(i));
        }
        List res = new ArrayList<>();
        res.add(address1);
        res.add(address2);
        return Result.success(res);
    }
}

