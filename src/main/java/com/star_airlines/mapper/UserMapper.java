package com.star_airlines.mapper;

import com.star_airlines.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login1(String email,String password);

    User login2(String phone,String password);
    User search(User userInfo);

    void register(User userInfo);

    User getUser(Integer id);

    void deleteAccount(Integer id);

    void updateAccount(User user);


    void updatePoint(Integer userId, Integer price);
}
