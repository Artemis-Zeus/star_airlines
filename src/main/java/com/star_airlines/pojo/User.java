package com.star_airlines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String address;
    private String email;
    private String phone;
    private String cardNum;
    private Integer point=0;
}
