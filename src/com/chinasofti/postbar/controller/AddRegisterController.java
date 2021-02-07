package com.chinasofti.postbar.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.service.LoginServiceImpl;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/loginController")
public class AddRegisterController extends BasicController {
	
	@Autowired
	@Qualifier("loginService")
	private LoginServiceImpl loginService;
	
	@RequestMapping(value="/addRegister")
	public void addRegister(HttpServletRequest request,HttpServletResponse response,String userName,String password,String regSex,String regAge,String regEmial) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		
		RegisterDto registerDto = new RegisterDto();
		String regUUID = this.getUUID();
		String userUUID = this.getUUID();
		String md5Password=this.md5(password);
		String regPhoto = "/postbar/headPhoto/default/default.jpg";
		String regTime=this.getStringDate(new Date());
		registerDto.setRegUUID(regUUID);
		registerDto.setUserUUID(userUUID);
		registerDto.setRegSex(regSex);
		registerDto.setRegAge(Integer.parseInt(regAge));
		registerDto.setRegEmial(regEmial);
		registerDto.setRegPhoto(regPhoto);
		registerDto.setRegTime(regTime);
		registerDto.setUserName(userName);
		registerDto.setLoginTime(regTime);
		registerDto.setAdmin("0");
		loginService.addRegister(registerDto);
		
		UserDto userDto = new UserDto();
		userDto.setUserUUID(userUUID);
		userDto.setUserName(userName);
		userDto.setPassword(md5Password);
		userDto.setAdmin("0");
		userDto.setLoginTime(regTime);
		loginService.addUser(userDto);
		
		String auSetUUID = this.getUUID();
		AudioDto audioDto = new AudioDto();
		audioDto.setAuSetUUID(auSetUUID);
		audioDto.setUserUUID(userUUID);
		audioDto.setAuSetSpd(5);
		audioDto.setAuSetPit(5);
		audioDto.setAuSetVol(5);
		audioDto.setAuSetVoiPer(0);
		loginService.addAudioDto(audioDto);
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
}
