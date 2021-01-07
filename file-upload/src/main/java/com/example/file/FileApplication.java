package com.example.file;

import com.example.file.filter.AccessAllowInterceptor;
import com.example.file.upload.FastDFSClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableWebMvc
@ServletComponentScan(basePackages = "com.example.file.filter")//所扫描的包路径必须包含该Filter
public class FileApplication extends WebMvcConfigurerAdapter {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }

    @Value("${dfs.client.conf}")
    private String dfsClientPath;

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }


    @Bean
    FastDFSClient fastDFSClient() {
        try {
            return new FastDFSClient(dfsClientPath);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessAllowInterceptor())
                .addPathPatterns(  "/**")
        ;

    }
}
