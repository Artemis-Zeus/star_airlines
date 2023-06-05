package com.star_airlines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class StarAirlinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarAirlinesApplication.class, args);
    }

}
