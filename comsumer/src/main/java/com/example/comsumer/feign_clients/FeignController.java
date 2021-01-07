package com.example.comsumer.feign_clients;

import com.example.comsumer.api.UserProvider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserProvider
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/30  19:50
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@FeignClient(value = UserProvider.SERVICE_ID)
public interface FeignController
        //extends  UserProvider
{
    /**
     * @des:  比较灵活，GetMapping和 RequestMapping都可以
     * 关于ribbon：使用openFeign时，无需配置ribbon。但是如果使用默认的RestTemplate，就需要配置ribbon
     * 最值得注意的是：方法返回值必须要有无参的构造函数，否则会报异常
     * @date 创建时间:2021/1/7
     * @version 1.0.0
     * @author Chuck
     **/
    @GetMapping( "user/test" )
    //@RequestMapping(value  ="user/test",method = RequestMethod.GET)
    String sendByForgetPwd(@RequestParam(value = "message") String message);
}
