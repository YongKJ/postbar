package com.chinasofti.postbar.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.chinasofti.postbar.service.UserManageService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userManageController")
public class UserManageController extends BasicController {
	
	@Autowired
	@Qualifier("userManageService")
	private UserManageService userManageService;
	
	@ModelAttribute
	@RequestMapping("/getUserList")
	public void getUserList(HttpServletRequest request,HttpServletResponse response,String userName,int pageIndex,int everyPageDataCount) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			List<RegisterDto> registerDtoList = userManageService.getAllRegisterDto();
			List<UserDto> userDtoList = userManageService.getAllUserDto();
			
			for(int i = 0; i < registerDtoList.size(); i++) {
				String userUUID = registerDtoList.get(i).getUserUUID();
				for(int j = 0; j < userDtoList.size(); j++) {
					if(userDtoList.get(j).getUserUUID().equals(userUUID)) {
						String theUserName = userDtoList.get(j).getUserName();
						String admin = userDtoList.get(j).getAdmin();
						registerDtoList.get(i).setUserName(theUserName);
						registerDtoList.get(i).setAdmin(admin);
						break;
					}
				}
			}
			
			json.put("postAllNum", 0);
			json.put("allPage", 0);
			json.put("pageIndex", 0);
			json.put("postList", "");
			json.put("admin", ((String)session.getAttribute("admin")));
			int postAllNum = registerDtoList.size();
			if (postAllNum > 0) {
				int allPage = 1;// 总页数变量
				// 计算总页数
				if ((postAllNum % everyPageDataCount) == 0) {
					allPage = postAllNum / everyPageDataCount;
				} else {
					allPage = postAllNum / everyPageDataCount + 1;
				}
				// 防止页码越界
				if (pageIndex < 0) {
					pageIndex = 0;
				} else if (pageIndex >= allPage) {
					pageIndex = allPage - 1;
				}
				
				List<RegisterDto> theRegisterDtoList = new ArrayList<RegisterDto>();
				if(!userName.equals("")) {
					for(int i = 0; i < registerDtoList.size(); i++) {
						if(registerDtoList.get(i).getUserName().toUpperCase().indexOf(userName.toUpperCase()) != -1) {
							theRegisterDtoList.add(registerDtoList.get(i));
						}
					}
					json.put("userAllNum", 0);
				}else {
					int a = pageIndex * everyPageDataCount;
					int b = a + everyPageDataCount > postAllNum ? postAllNum : a + everyPageDataCount;
					for(int i = a; i < b ; i++) {
						theRegisterDtoList.add(registerDtoList.get(i));
					}
					json.put("userAllNum", postAllNum);
				}
				
				
				json.put("allPage", allPage);
				json.put("pageIndex", pageIndex);
				json.put("registerList", theRegisterDtoList);
			}
		
		this.writeJson(json.toString(), response);
		
		}
	}
	
	@ModelAttribute
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,String userUUID) {
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			userUUID = userUUID.substring(0, userUUID.length() - 1);
			String[] userUUIDs = userUUID.split(",");
			
			for(int i = 0; i < userUUIDs.length; i++) {
				userManageService.delUserDtoByUserUUID(userUUIDs[i]);
				userManageService.delRegisterDtoByUserUUID(userUUIDs[i]);
				userManageService.delAudioDtoByUserUUID(userUUIDs[i]);
				userManageService.delPostDtoByUserUUID(userUUIDs[i]);
				userManageService.delPostPraiseDtoByUserUUID(userUUIDs[i]);
				userManageService.delCommentDtoByUserUUID(userUUIDs[i]);
				userManageService.delCommentPraiseDtoByUserUUID(userUUIDs[i]);
			}
		}
		
		this.writeJson(json.toString(), response);
	}
	
}
