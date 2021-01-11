package com.example.comsumer.controller;

import com.example.commons.vo.ResponseEntity;
import com.example.comsumer.feign_clients.FeignFileUploadWithNacos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class NacosFeignFileController {

    @Resource
    private FeignFileUploadWithNacos feignControllerFile;


    /**
     * @method name:
     * @des:  这样会报错
     * @param :
     * @return type:
     * @date 创建时间:2021/1/11
     * @version 1.0.0
     * @author Chuck
     **/

    @ApiOperation(value = "使用openFeign实现单文件上传", response = String.class, notes = "使用openFeign实现单文件上传,返回可访问的路径")
    @PostMapping("/feign/upload")
    public ResponseEntity uploadFileAction(HttpServletRequest request) {
    //public ResponseEntity uploadFileAction(HttpServletRequest request) {
        return feignControllerFile.uploadFileAction(request);
    }

    /**
     * @method name:
     * @des:  这样会报错
     * @param :
     * @return type:
     * @date 创建时间:2021/1/11
     * @version 1.0.0
     * @author Chuck
     **/

    @ApiOperation(value = "使用openFeign实现多文件上传",  response = ArrayList.class, notes = "使用openFeign实现文件批量上传,返回list<String>")
    @PostMapping("/feign/multi_upload")
    //public ResponseEntity multiImportAction(HttpServletRequest request) {
    public ResponseEntity multiImportAction(HttpServletRequest request) {
        return feignControllerFile.multiImportAction(request);
    }




    @ApiOperation(value = "nacos+feign 单文件上传2", response = String.class, notes = "nacos+feign，单文件上传2")
    @PostMapping("/feign/upload2")
    public ResponseEntity uploadFileAction2(@RequestParam("file") MultipartFile file) {
        ResponseEntity responseEntity = feignControllerFile.uploadFileAction2(file);
        return responseEntity;
    }

    @ApiOperation(value = "nacos+feign 多文件上传2",  response = ArrayList.class, notes = "nacos+feign 文件批量上传,返回list<String>")
    @PostMapping("/feign/multi_upload2")
    public ResponseEntity multiImportAction2(@RequestParam("file")  List<MultipartFile> files) {
        return feignControllerFile.multiImportAction2(files);
    }


}
