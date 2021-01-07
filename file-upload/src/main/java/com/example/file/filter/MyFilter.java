package com.example.file.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * MyFilter
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/7  17:48
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {


        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String header = req.getHeader("Content-Type");
            MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
            //mutableRequest.putHeader("Content-Type", "multipart/form-data");
            mutableRequest.putHeader("Content-Type", "multipart/form-data; boundary=-------------------------acebdf13572468");
            header = mutableRequest.getHeader("Content-Type");
            chain.doFilter(mutableRequest, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void destroy() {
    }
}


