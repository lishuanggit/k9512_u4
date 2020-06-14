package com.team.housebackapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(value = "com.team.housebackapi.mapper")
@ServletComponentScan("com.team.housebackapi.filter")
public class HouseBackapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseBackapiApplication.class, args);
    }

}
