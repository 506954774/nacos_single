package com.example.comsumer.controller;

import com.example.commons.vo.ResponseEntity;
import com.example.comsumer.feign_clients.FeignFileUploadWithNacos2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
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
public class NacosFeignFileController2 {

    @Resource
    private FeignFileUploadWithNacos2 okFeignFileUpload;


    @ApiOperation(value = "nacos+feign  单文件上传", response = String.class, notes = "nacos+feign ，单文件上传")
    @PostMapping("/feign2/upload2")
    public ResponseEntity uploadFileAction2(@RequestParam("file") MultipartFile file) {
        ResponseEntity responseEntity = okFeignFileUpload.uploadFileAction2(file);
        return responseEntity;
    }


}
