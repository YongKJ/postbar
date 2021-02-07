package com.chinasofti.postbar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.service.PerSetUpService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/perSetUpController")
public class PerSetUpController extends BasicController {
	
	@Autowired
	@Qualifier("perSetUpService")
	private PerSetUpService perSetUpService;
	
	@ModelAttribute
	@RequestMapping("/selectPerSetUp")
	public void selectPerSetUp(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			String userUUID=(String) session.getAttribute("id");
			RegisterDto registerDto = perSetUpService.getRegisterDtoByUserUUID(userUUID);
			UserDto userDto = perSetUpService.getUserDtoDtoByUserUUID(userUUID);
			
			registerDto.setUserName(userDto.getUserName());
			
			json.put("registerDto", registerDto);
		}
		
		this.writeJson(json.toString(), response);
		
	}
	
	@ModelAttribute
	@RequestMapping("/updatePerSetUp")
	public void updatePerSetUp(HttpServletRequest request,HttpServletResponse response, String regUUID, String userName, String regSex, int regAge, String regEmial, String oldName) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		json.put("error", "");
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			perSetUpService.UpdateRegisterDtoByRegUUID(regUUID, regSex, regAge, regEmial);
			String userUUID=(String) session.getAttribute("id");
			perSetUpService.UpdateUserDtoByRegUUID(userUUID, userName);
		}
		
		this.writeJson(json.toString(), response);
		
	}

}
