package com.example.userprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * UserProvider
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/30  19:50
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/

@Slf4j
@RestController
public class UserProvider {

    @Value(value = "${server.port}")
    private int port;

    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    //@GetMapping("/test")
    @RequestMapping(value  ="/user/test",method = RequestMethod.GET)
    public String sendByForgetPwd(@ApiParam(name = "message", value = "客户端传来的数据", required = true)
                                          @RequestParam(value = "message") String message) {

        String response= MessageFormat.format("服务提供者端口：{0}，收到调用者的message：{1}",String.valueOf(port),message);
        //return new ResponseEntity<String>(response,HttpStatus.OK);

        return response;
    }


}
