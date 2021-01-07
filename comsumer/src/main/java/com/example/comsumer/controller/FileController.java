package com.example.comsumer.controller;

import com.example.commons.vo.ResponseEntity;
import com.example.comsumer.feign_clients.FeignControllerFile;
import com.example.comsumer.feign_clients.FeignControllerUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import feign.Headers;
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
public class FileController {

    @Resource
    private FeignControllerFile feignControllerFile;


    @ApiOperation(value = "使用openFeign实现单文件上传", response = String.class, notes = "使用openFeign实现单文件上传,返回可访问的路径")
    @PostMapping("/feign/upload")
    public ResponseEntity uploadFileAction(HttpServletRequest request) {
    //public ResponseEntity uploadFileAction(HttpServletRequest request) {
        return feignControllerFile.uploadFileAction(request);
    }

    @ApiOperation(value = "使用openFeign实现多文件上传",  response = ArrayList.class, notes = "使用openFeign实现文件批量上传,返回list<String>")
    @PostMapping("/feign/multi_upload")
    //public ResponseEntity multiImportAction(HttpServletRequest request) {
    public ResponseEntity multiImportAction(HttpServletRequest request) {
        return feignControllerFile.multiImportAction(request);
    }

    @Headers({"Content-Type: multipart/form-data"})
    @ApiOperation(value = "单文件上传2", response = String.class, notes = "单文件上传,返回可访问的路径")
    @PostMapping("/feign/upload2")
    public ResponseEntity uploadFileAction2(@RequestParam("file") MultipartFile file) {
        ResponseEntity responseEntity = feignControllerFile.uploadFileAction2(file);
        return responseEntity;
    }

    @ApiOperation(value = "多文件上传2",  response = ArrayList.class, notes = "文件批量上传,返回list<String>")
    @PostMapping("/feign/multi_upload2")
    public ResponseEntity multiImportAction2(@RequestParam("file")  List<MultipartFile> files) {
        return feignControllerFile.multiImportAction2(files);
    }


}
