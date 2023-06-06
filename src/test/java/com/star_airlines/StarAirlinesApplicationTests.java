package com.star_airlines;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class StarAirlinesApplicationTests {

    @Test
    public  void testGenJwt(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");
        String Jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
        System.out.println(Jwt);
    }

//    时间转换测试
    @Test
    public void TimeConvert(){
        LocalDate date= LocalDate.of(2023,6,6);
        LocalDateTime localDateTime = date.atStartOfDay();
        LocalDateTime now=LocalDateTime.now();
        if (now.isAfter(localDateTime)){
            System.out.println(now);
        }
        else {
            System.out.println(localDateTime);
        }

    }

}
