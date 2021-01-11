package com.example.comsumer.controller;

import com.example.commons.vo.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * TestController
 *
 *
 实际测试结果是，无法调起远程文件上传服务

 *
         *
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/31  17:31
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@RestController
public class RestTemplateController {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Resource
    private   RestTemplate restTemplate;



    @ApiOperation(value = "测试使用RestTemplate实现RPC，普通get请求", notes = "使用RestTemplate实现RPC，普通get请求")
    @RequestMapping(value = "/resttemplate_rpc", method = RequestMethod.GET)
    public String echo(@ApiParam(name = "message", value = "客户端传来的数据", required = true)
                           @RequestParam(value = "message") String message) {

        String uri = "/user/test/?message="+message;
        String url = "http://user"+uri;


        return restTemplate.getForObject(url, String.class);
    }



    @ApiOperation(value = "使用RestTemplate实现RPC,文件上传", notes = "使用RestTemplate实现RPC,文件上传")
    @RequestMapping(value = "/resttemplate_rpc/file_upload",method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object upload(@RequestParam(value = "file") MultipartFile file) {

        String uri = "/upload";
        String url = "http://file"+uri;

       // return restTemplate.postForEntity(url,file,ResponseEntity.class);


       // return restTemplate.getForObject(url, String.class);


        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, MultipartFile>> request = new HttpEntity<MultiValueMap<String, MultipartFile>>(
                popHeaders(file), headers);


        org.springframework.http.ResponseEntity<ResponseEntityAccessToken > accessTokenResponseEntity
                = restTemplate.postForEntity(url, request,ResponseEntityAccessToken.class);

        return    accessTokenResponseEntity.getBody().getResult();
    }

    public static class ResponseEntityAccessToken extends ResponseEntity<String>{
        public ResponseEntityAccessToken( ) {
            super(true);
        }


        public ResponseEntityAccessToken(boolean success) {
            super(success);
        }

        public ResponseEntityAccessToken(String code, boolean success, String message) {
            super(code, success, message);
        }
    }

    //组装请求体
    protected MultiValueMap<String, MultipartFile> popHeaders(MultipartFile file) {

        MultiValueMap<String, MultipartFile> map = new LinkedMultiValueMap<String, MultipartFile>();

        map.add("file", file);

        return map;
    }
}
