package com.example.comsumer.feign_clients;

import com.example.commons.vo.ResponseEntity;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * UploadFeginClient
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/11  15:48
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 * https://blog.csdn.net/keypanj2ee/article/details/105426851
 **/
@FeignClient(value="file2" , configuration = FeignFileUploadWithNacos2.ClientConfiguration.class)
public interface FeignFileUploadWithNacos2 {
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


    /**
     * 多文件上传
     * @param files
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/multi_upload2",method = RequestMethod.POST ,
            // produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity multiImportAction2(@RequestParam(value = "file") List<MultipartFile> files);


//文件配置转换  (内部类)
    class ClientConfiguration{
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        /**
         * @Description // 此处注入的是： ObjectFactory<HttpMessageConverters>
         * @Date 23:04 2020/4/9
         * @Param []
         * @return feign.form.spring.SpringFormEncoder
         **/
        @Bean
        public Encoder feignEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

    }

}
