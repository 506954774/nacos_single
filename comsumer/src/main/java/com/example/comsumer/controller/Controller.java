package com.example.comsumer.controller;

import com.example.comsumer.feign_clients.FeignController;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@RequestMapping("/user")
public class Controller {

    @Resource
    private FeignController feignController;


    @ApiOperation(value = "使用FeignController实现RPC", notes = "使用FeignController实现RPC")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "message", value = "客户端传来的数据", required = true)
                                          @RequestParam(value = "message") String message) {
        return feignController.sendByForgetPwd(message);
    }




}
