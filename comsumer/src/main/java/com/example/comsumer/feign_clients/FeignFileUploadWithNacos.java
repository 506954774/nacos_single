package com.example.comsumer.feign_clients;

import com.example.commons.vo.ResponseEntity;
import com.example.comsumer.config.FeignSupportConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * UserProvider
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/30  19:50
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@FeignClient(value = "file",configuration = FeignSupportConfig.class)
//@FeignClient(value = "file" , configuration = OkFeignFileUpload.ClientConfiguration.class)
public interface FeignFileUploadWithNacos
        //extends  UserProvider
{

    //@PostMapping("/upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileAction(HttpServletRequest request);

    //@PostMapping("/multi_upload")
    @RequestMapping(value = "/multi_upload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity multiImportAction(HttpServletRequest request) ;


    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload2",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity uploadFileAction2(@RequestPart(value = "file") MultipartFile file)  ;



    //@PostMapping("/multi_upload2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestMapping(value = "/multi_upload2",method = RequestMethod.POST ,
           // produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity multiImportAction2(@RequestParam(value = "file")  List<MultipartFile> files);
}
