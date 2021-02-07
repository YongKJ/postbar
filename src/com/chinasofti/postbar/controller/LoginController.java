package com.chinasofti.postbar.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.service.LoginServiceImpl;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/loginController")
public class LoginController extends BasicController {
	
	@Autowired
	@Qualifier("loginService")
	private LoginServiceImpl loginService;
	
	@RequestMapping(value="/login")
	public void login(HttpServletRequest request,HttpServletResponse response,String userName,String password) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		String md5Password=this.md5(password); // 将原始密码进行MD5加密，数据库保存的是加密后的数据
		UserDto userDto = loginService.getUserByUserNameAndPassword(userName, md5Password);
		if(userDto == null) {
			json.put("message", "用户名和密码错误");
		}else {
			HttpSession session = request.getSession();
			
//			--这里需要将id,username,admin,datetime四个属性存入session中，暂时不写
			session.setAttribute("username", userDto.getUserName());
			session.setAttribute("admin", userDto.getAdmin());
			session.setAttribute("id", userDto.getUserUUID());
			
			Date datetime = this.getDate();
			if(userDto.getLoginTime() == null) {
				session.setAttribute("datatime", datetime);
			}else{
				session.setAttribute("datatime", userDto.getLoginTime());
			}
			 
			json.put("admin", userDto.getAdmin());// 将用户权限级别添加到json对象中
		}
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@RequestMapping(value="/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@RequestMapping(value="/getAdmin")
	public void getAdmin(HttpServletRequest request,HttpServletResponse response) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		
		HttpSession session = request.getSession();
		String myAdmin=(String) session.getAttribute("admin");// 从session获得当前用户的权限级别
		
		json.put("admin", myAdmin);
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@RequestMapping(value="/editPassword")
	public void editPassword(HttpServletRequest request,HttpServletResponse response,String oldPassword, String newPassword) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		json.put("error", ""); // 在对象中先添加入一个数据
		
		HttpSession session=request.getSession();
		String userUUID=(String) session.getAttribute("id");
		String md5OldPassword=this.md5(oldPassword);
		String md5NewPassword=this.md5(newPassword);
		
		loginService.updateUserPassword(userUUID, md5OldPassword, md5NewPassword);
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}

}
