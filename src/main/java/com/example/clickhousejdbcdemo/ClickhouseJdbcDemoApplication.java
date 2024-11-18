package com.example.clickhousejdbcdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.clickhousejdbcdemo.dao"})
public class ClickhouseJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickhouseJdbcDemoApplication.class, args);
    }

}
