package com.star_airlines.controller;

import com.star_airlines.pojo.Record;
import com.star_airlines.pojo.Result;
import com.star_airlines.pojo.User;
import com.star_airlines.service.SearchService;
import com.star_airlines.service.UserService;
import com.star_airlines.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
public class BookController {
    @Autowired
    private UserService userService;
    @Autowired
    private SearchService searchService;

    public Result book(String token, Record record, Integer type) {
        log.info("预定事务开始");
//        获取对应用户id
        Integer id = (Integer) JwtUtil.parseJWT(token).get("id");
        User user = userService.getUser(id);
//        检测付款方式是否补全
        if (user.getCardNum() == null || user.getAddress() == null) {
            return Result.error("Missing personal payment information");
        }
//        填充用户信息
        record.setUserId(id);
        record.setType(1);
        Record re = searchService.searchRecord(record.getId(), record.getUserId());


//        开始判别预定类型 1-flight 2-hotel 3-car
        if (type == 1) {
//            当为航班且记录中查询到对应单号，判断是否已订机票或是想退票后重新购买
            if (re != null) {
                if (re.getType() == 1) {
                    return Result.error("当前航班已订票,请勿重复订票");
                } else {
                    searchService.update_status(re.getId(), LocalDateTime.now());
                }
            }
        }
        record.setId(UUID.randomUUID().toString());
        record.setUpdateTime(LocalDateTime.now());
        searchService.book_ticket(record);
        log.info("预订成功");
        return Result.success("预订成功", record);
    }

    @PostMapping("/book/flight")
    public Result book_flight(@RequestHeader("token") String token_key, @RequestBody Record record) {
        log.info("订票事务开始");
        return book(token_key, record, 1);
    }

    @PostMapping("/book/hotel")
    public Result book_hotel(@RequestHeader("token") String token_key, @RequestBody Record record) {
        log.info("订票事务开始");
        return book(token_key, record, 2);
    }

    @PostMapping("/book/car")
    public Result book_car(@RequestHeader("token") String token_key, @RequestBody Record record) {
        log.info("订票事务开始");
        return book(token_key, record, 3);
    }

    //    退票
    @PostMapping("refund")
    public Result refund(@RequestHeader("token") String token_key, @RequestBody String id) {
        searchService.refund(id);
        Integer UserId = (Integer) JwtUtil.parseJWT(token_key).get("id");
        Record re = searchService.searchRecord(id, UserId);
        Integer price = re.getPrice();
        userService.updatePoint(UserId, -price);
        log.info("退票成功");
        return Result.success("退票成功");
    }
}
