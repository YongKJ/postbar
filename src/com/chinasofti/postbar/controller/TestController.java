package com.chinasofti.postbar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(" --------->> 访问test页面");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>你好，欢迎光临！</h1>");
		out.close();
	}

}
