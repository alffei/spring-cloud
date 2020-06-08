package com.gd.bizuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.gd"})
@MapperScan(basePackages = {"com.gd.bizuser.dao.mapper"})
public class BizUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizUserApplication.class, args);
    }

}
