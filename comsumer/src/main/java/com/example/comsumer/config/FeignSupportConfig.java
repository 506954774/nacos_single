package com.example.comsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;

/**
 * FeignSupportConfig
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/8  11:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Configuration
public class FeignSupportConfig {
    @Bean
    public Encoder feignFormEncoder() {
        return new FeignSpringFormEncoder();
    }
}
