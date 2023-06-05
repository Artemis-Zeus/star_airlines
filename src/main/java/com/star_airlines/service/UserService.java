package com.star_airlines.service;

import com.star_airlines.pojo.Record;
import com.star_airlines.pojo.User;

import java.util.List;

public interface UserService {
    User login1(String email,String password);

    User login2(String phone,String password);


    User search(User userInfo);

    void register(User userInfo);

    User getUser(Integer id);

    void deleteAccount(Integer id);

    void updateAccount(User user);


    void updatePoint(Integer userId, Integer price);

    List<Record> getRecords(Integer id);
}
