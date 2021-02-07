package com.chinasofti.postbar.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProcessInterceptor implements HandlerInterceptor {

	  @Override
	  public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
	    httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	    httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
	    httpServletResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
	    httpServletResponse.setHeader("X-Powered-By","Jetty");

	    String method= httpServletRequest.getMethod();
	    if (method.equals("OPTIONS")){
	    	System.out.println(method);
	        httpServletResponse.setStatus(200);
	        return false;
	    }
	    System.out.println(method);
	    return true;
	  }
	  
	  @Override
	  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
	  }
	  
	  @Override
	  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	  }
	  
}
