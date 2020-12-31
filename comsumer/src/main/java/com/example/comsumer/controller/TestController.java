package com.example.comsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * TestController
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/31  17:31
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@RestController
public class TestController {

    @Autowired
    private   RestTemplate restTemplate;

   /* @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    @ApiOperation(value = "使用RestTemplate实现RPC", notes = "使用RestTemplate实现RPC")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String echo(@ApiParam(name = "message", value = "客户端传来的数据", required = true)
                           @RequestParam(value = "message") String message) {

        String uri = "/user/test/?message="+message;
        String url = "http://user"+uri;


        return restTemplate.getForObject(url, String.class);
    }
}
