package com.mao.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//HandlerInterceptorAdapter
//跨域设置
public class AjaxCheckSession extends HandlerInterceptorAdapter {

   public boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse, Object handler) throws Exception {
       System.out.println("hhhh"); 
       HttpServletResponse res = (HttpServletResponse) servletResponse;
       HttpServletRequest request=(HttpServletRequest)servletRequest;
       res.setContentType("textml;charset=UTF-8");
       res.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
       res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
       res.setHeader("Access-Control-Max-Age", "0");
       res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
       res.setHeader("Access-Control-Allow-Credentials", "true");
       res.setHeader("XDomainRequestAllowed","1");

        return super.preHandle(request, res, handler);
    } 

}