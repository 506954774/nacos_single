package com.example.comsumer.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * MultipartSupportConfig
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/7  16:28
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
//@Configuration
public class MultipartSupportConfig {
   /* @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

  @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Scope("prototype")
    @Primary
    @Bean
    public Encoder feignFormecoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }*/
}
