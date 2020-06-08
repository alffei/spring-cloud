package com.gd.zuulgateway.config;

import com.gd.zuulgateway.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description : zuul配置
 **/
@Configuration
public class ZuulConfig {

    @Bean
    public JWTFilter initJWTFilter(){
        return new JWTFilter();
    }
}
