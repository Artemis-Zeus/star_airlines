package com.star_airlines.service.impl;

import com.star_airlines.mapper.UserMapper;
import com.star_airlines.pojo.Record;
import com.star_airlines.pojo.User;
import com.star_airlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login1(String email,String password) {
        return userMapper.login1(email,password);
    }

    @Override
    public User login2(String phone,String password) {
        return userMapper.login2(phone,password);
    }

    @Override
    public User search(User userInfo) {
        return userMapper.search(userInfo);
    }

    @Override
    public void register(User userInfo) {
        userMapper.register(userInfo);
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    @Override
    public void deleteAccount(Integer id) {
        userMapper.deleteAccount(id);
    }

    @Override
    public void updateAccount(User user) {
        userMapper.updateAccount(user);
    }


    @Override
    public void updatePoint(Integer userId, Integer price) {
        userMapper.updatePoint(userId,price);
    }

    @Override
    public List<Record> recordList(Integer id) {
        return userMapper.recordList(id);
    }

}
