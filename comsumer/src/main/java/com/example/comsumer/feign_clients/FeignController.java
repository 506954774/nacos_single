package com.example.comsumer.feign_clients;

import com.example.comsumer.api.UserProvider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * UserProvider
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/30  19:50
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@FeignClient(value = UserProvider.SERVICE_ID)
public interface FeignController extends UserProvider {
}
