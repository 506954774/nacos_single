/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.file.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shawn
 * @datetime 2017-8-29 10:32:14
 * @version 1.0.0
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public class AccessAllowInterceptor extends HandlerInterceptorAdapter {




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	

    	
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if(uri.contains("upload")){
        }




        return super.preHandle(request, response, handler);
        
    }
}