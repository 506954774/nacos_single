package com.example.comsumer.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

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
public interface UserProvider {

    final String SERVICE_ID="user";

    /**
     * @method name:
     * @des:  注意，返回值的类型，必须要有无参的构造函数，否则会报错
     * @param :
     * @return type:
     * @date 创建时间:2021/1/7
     * @version 1.0.0
     * @author Chuck
     **/

    @RequestMapping(value  ="/user/test",method = RequestMethod.GET)
    public String sendByForgetPwd(@RequestParam(value = "message") String message);

}
