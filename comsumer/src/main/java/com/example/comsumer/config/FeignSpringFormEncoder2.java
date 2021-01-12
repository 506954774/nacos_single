package com.example.comsumer.config;

/**
 * FeignSpringFormEncoder2
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/12  10:25
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/

import feign.form.spring.SpringFormEncoder;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * author：czq
 * time: 2020/9/27 15:35
 * description: 多文件上传配置
 **/
public class FeignSpringFormEncoder2 extends FormEncoder {

    public FeignSpringFormEncoder2() {
        this(new Default());
    }

    public FeignSpringFormEncoder2(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) this.getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        HashMap data;
        if (bodyType.equals(MultipartFile[].class)) {
            MultipartFile[] files = (MultipartFile[]) object;
            data = new HashMap();
            //data添加数组
            if (files != null) {
                data.put(files.length == 0 ? "" : files[0].getName(), files);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            MultipartFile file = (MultipartFile) object;
            data = (HashMap) Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (this.isMultipartFileCollection(object)) {
            Iterable<?> iterable = (Iterable) object;
            data = new HashMap();
            Iterator var13 = iterable.iterator();

            while (var13.hasNext()) {
                Object item = var13.next();
                MultipartFile file = (MultipartFile) item;
                data.put(file.getName(), file);
            }
        } else {
            super.encode(object, bodyType, template);
        }

    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        } else {
            Iterable<?> iterable = (Iterable) object;
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext() && iterator.next() instanceof MultipartFile;
        }
    }
}
