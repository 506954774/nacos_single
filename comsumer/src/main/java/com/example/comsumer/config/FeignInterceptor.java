package com.example.comsumer.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * FeignInterceptor
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/8  10:51
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {

            String contentTypeKey="Content-Type";

            HttpServletRequest request = attributes.getRequest();

            String header = request.getHeader(contentTypeKey);
            System.out.println(header);

            //template.removeHeader("Content-Type");

            //将Content-Type信息放入header中
            //template.header(contentTypeKey,request.getHeader("multipart/form-data; boundary=--------------------------687878955487037618572546"));
        }
    }
}