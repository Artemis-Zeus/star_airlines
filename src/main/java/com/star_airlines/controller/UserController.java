package com.star_airlines.controller;
import com.star_airlines.pojo.Flights;
import com.star_airlines.pojo.Record;
import com.star_airlines.pojo.Result;
import com.star_airlines.pojo.User;
import com.star_airlines.service.SearchService;
import com.star_airlines.service.UserService;
import com.star_airlines.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin

@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SearchService searchService;


    //    登录操作
    @PostMapping("/login")
    public Result login(String account, String password) {
        User user;
        if (account.contains("@")) {
            log.info("当前在邮箱操作");
            user = userService.login1(account, password);
        } else {
            log.info("当前在手机操作");
            user = userService.login2(account, password);
        }
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            Object token_key = JwtUtil.generateJwt(claims);
            return Result.success("success", token_key);
        } else {
            return Result.error("The account or password is incorrect");
        }
    }


    //    注册操作
    @PostMapping("/register")
    public Result register(@RequestBody User userInfo) {
        log.info("当前在注册操作");
        User user = userService.search(userInfo);
        if (user != null) {
            return Result.error("this email has been used.");
        } else {
            userService.register(userInfo);
            return Result.success("create account successfully.");
        }
    }

    //    查看用户信息
    @GetMapping("/account")
    public Result getUser(@RequestHeader("token") String token_key) {
        log.info("get user's info");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        User user = userService.getUser(id);
        return Result.success(user);
    }

    //    删除用户
    @DeleteMapping("/account")
    public Result deleteAccount(@RequestHeader("token") String token_key) {
        log.info("delete account");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        userService.deleteAccount(id);
        return Result.success("delete successfully");
    }

    //    修改账户信息
    @PostMapping("/account")
    public Result updateAccount(@RequestHeader("token") String token_key, @RequestBody User user) {
        log.info("update account");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        user.setId(id);
        userService.updateAccount(user);
        return Result.success("update successfully");
    }

    //    获取积分
    @GetMapping("/account/point")
    public Result userPoint(@RequestHeader("token") String token_key) {
        log.info("get point");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        User user = userService.getUser(id);
        return Result.success("query point successfully", user.getPoint());
    }
    @GetMapping("/account/records")
    public Result userRecords(@RequestHeader("token") String token_key){
        log.info("get records");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        List<Record> records = userService.recordList(id);
        return Result.success(records);
    }
    @GetMapping("/account/flight_record")
    public Result flightRecord(@RequestHeader("token") String token_key){
        log.info("get flight records");
        Integer id=(Integer) JwtUtil.parseJWT(token_key).get("id");
        List<Record> records = userService.recordList(id);
        List<Map> flightRecords=new ArrayList<>();
        for (Record record : records) {
            if (record.getFlightId() != null) {
                Map<String, Object> flightRecord=new HashMap<>();
                flightRecord.put("flightInfo", searchService.getFlight(record.getFlightId()));
                flightRecord.put("recordInfo", record);
                flightRecords.add(flightRecord);
            }
        }
        return Result.success(flightRecords);
    }
    @GetMapping("/account/hotel_record")
    public Result hotelRecord(@RequestHeader("token") String token_key){
        log.info("get hotel records");
        Integer id=(Integer) JwtUtil.parseJWT(token_key).get("id");
        List<Record> records = userService.recordList(id);
        List<Map> HotelRecords=new ArrayList<>();
        for (Record record : records) {
            if (record.getHotelId() != null) {
                Map<String, Object> hotelRecord=new HashMap<>();
                hotelRecord.put("hotelInfo", searchService.getHotel(record.getHotelId()));
                hotelRecord.put("recordInfo", record);
                HotelRecords.add(hotelRecord);
            }
        }
        return Result.success(HotelRecords);
    }
}
