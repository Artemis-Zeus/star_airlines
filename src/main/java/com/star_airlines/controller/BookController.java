package com.star_airlines.controller;

import com.star_airlines.pojo.Record;
import com.star_airlines.pojo.Result;
import com.star_airlines.pojo.User;
import com.star_airlines.service.BookService;
import com.star_airlines.service.SearchService;
import com.star_airlines.service.UserService;
import com.star_airlines.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private UserService userService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private BookService bookService;


    @PostMapping("/book")
    public Result book_flight(@RequestHeader("token") String token_key, @RequestBody Record record) {
        log.info("订票事务开始");
        Integer id = (Integer) JwtUtil.parseJWT(token_key).get("id");
        System.out.println(record);
        Integer mount = 0;

//        校验用户信息阶段
        User user = userService.getUser(id);
//        检测付款方式是否补全
        if (user.getCardNum() == null || user.getPhone() == null) {
            return Result.error("Missing personal payment information");
        }
//        填充用户信息，检测是否重复购票
        record.setUserId(id);
        record.setType(1);

//        机票阶段
        if (record.getFlightId() != null) {
            Record re = searchService.searchRecord(record.getFlightId(), id);
            //            当为航班且记录中查询到对应单号，判断是否已订机票或是想退票后重新购买
            if (re != null && re.getType() == 1) {
                return Result.error("当前航班已订票,请勿重复订票");
            }
            mount += searchService.getFlightPrice(record.getFlightId());
        }
        if (record.getHotelId() != null) {
            mount += searchService.getHotelPrice(record.getHotelId()) * record.getDays();
        }
        record.setPrice(mount);
        if (record.getUsePoint() > 1) {
            userService.updatePoint(id, -500);
        }


        record.setId(UUID.randomUUID().toString());
        record.setUpdateTime(LocalDateTime.now());
        bookService.book_ticket(record);
        userService.updatePoint(id,mount);
        return Result.success("预订成功", record);
    }

    //    退票
    @PostMapping("/book/refund")
    public Result refund(@RequestHeader("token") String token_key, String id) {
        bookService.refund(id);
        log.info("pass");
        Record record = searchService.getRecord(id);
        if(record.getUsePoint()==1){
            userService.updatePoint(record.getUserId(), 500);
        }
        userService.updatePoint(record.getUserId(), -record.getPrice());
        return Result.success("退款成功",record.getPrice());
    }
}
