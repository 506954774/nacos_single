package com.example.file.action;

import com.example.commons.vo.ResponseEntity;
import com.example.file.exception.AdminErrorCode;
import com.example.file.exception.AdminException;
import com.example.file.upload.FastDFSClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Jason on 2019/11/4.
 */
@Slf4j
@RestController
public class FileUploadController {


    @Resource
    private FastDFSClient fastDFSClient;
    @Value("${dfs.client.host}")
    private String dfsHost;
    @Value("${dfs.max.size}")
    private int maxSize;

    private static final String LOGGER_PREFIX = "【文件上传接口】";

    @ApiOperation(value = "单文件上传", response = String.class, notes = "单文件上传,返回可访问的路径")
    @PostMapping("/upload")
    public ResponseEntity uploadFileAction(HttpServletRequest request) {
        try {
            String result= uploadFile(request);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "多文件上传",  response = ArrayList.class, notes = "文件批量上传,返回list<String>")
    @PostMapping("/multi_upload")
    public ResponseEntity multiImportAction(HttpServletRequest request) {
        try {
            List<String> result= multiImport(request);
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }


    @ApiOperation(value = "单文件上传2", response = String.class, notes = "单文件上传,返回可访问的路径")
    @RequestMapping(value = "/upload2",method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileAction2(@RequestParam(value = "file")  MultipartFile file) {
        try {
            String result= uploadFile(file);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "多文件上传2",  response = ArrayList.class, notes = "文件批量上传,返回list<String>")
    @RequestMapping(value = "/multi_upload2",method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity multiImportAction2(@RequestParam(value = "file")  MultipartFile[] files) {
        try {
            List<String> result= multiImport(files);
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }


    public String uploadFile(HttpServletRequest request) throws AdminException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mFile = multipartRequest.getFile("file");
        if(mFile==null){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY,"上传内容为空!");
        }
        else {
            try {
                return  uploadFile(mFile);
            } catch (AdminException e) {
                throw e;
            }
        }
    }

    public String uploadFile(MultipartFile mFile) throws AdminException {
        if (mFile == null) {
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, "上传内容为空!");
        }
        if (mFile.getSize() > maxSize * 1024 * 1024) {
            log.warn( LOGGER_PREFIX + "[文件上传]文件内容过大，上传失败！文件大小[" + mFile.getSize() + "].");
            throw new AdminException(AdminErrorCode.FILE_TOO_LARGE,"文件内容过大，上传失败！文件大小[" + mFile.getSize() + "].");
        }

        // 获取后缀名
        String originalFileName = mFile.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileType.equalsIgnoreCase("jpeg") && !fileType.equalsIgnoreCase("jpg") && !fileType.equalsIgnoreCase("png")) {
            log.warn( LOGGER_PREFIX + "[文件上传]文件类型不合法，上传失败！文件类型[" + fileType + "].");
            throw new AdminException(AdminErrorCode.FILE_TYPE_INVALID, "文件类型不合法，上传失败！文件类型[" + fileType + "].");
        }

        try {
            InputStream inputStream = mFile.getInputStream();
            byte[] file_buff = null;
            if (inputStream != null) {
                int len = inputStream.available();
                file_buff = new byte[len];
                inputStream.read(file_buff);
            }

            String fileUrl = fastDFSClient.uploadFile(file_buff, fileType);
            log.info( LOGGER_PREFIX + "[文件上传]文件上传成功！文件链接url[" + fileUrl + "].");
            return "http://"+dfsHost + "/" + fileUrl;
        } catch (Exception e) {
            log.error( LOGGER_PREFIX + "[文件上传]文件上传失败！", e);
            throw new AdminException(AdminErrorCode.REQUEST_EXCEPTION, "文件上传失败！");
        }
    }


    public List<String> multiImport(HttpServletRequest request) throws AdminException {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if(files==null||files.size()==0){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, "上传内容为空!");
        }
        else {
            List<String> result = new ArrayList<>();
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            for (MultipartFile singleFile:files) {
                try {
                    String url = uploadFile(singleFile);
                    result.add(url);
                } catch (AdminException e) {
                    throw e;
                }
            }
            return result;
        }
    }
    public List<String> multiImport(MultipartFile[] files) throws AdminException {

        if(files==null||files.length==0){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, "上传内容为空!");
        }
        else {
            List<String> result = new ArrayList<>();
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            for (MultipartFile singleFile:files) {
                try {
                    String url = uploadFile(singleFile);
                    result.add(url);
                } catch (AdminException e) {
                    throw e;
                }
            }
            return result;
        }
    }
    public List<String> multiImport(List<MultipartFile> files) throws AdminException {

        if(files==null||files.size()==0){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, "上传内容为空!");
        }
        else {
            List<String> result = new ArrayList<>();
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            for (MultipartFile singleFile:files) {
                try {
                    String url = uploadFile(singleFile);
                    result.add(url);
                } catch (AdminException e) {
                    throw e;
                }
            }
            return result;
        }
    }
}
