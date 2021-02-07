package com.chinasofti.postbar.controller;

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
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.service.MyPostService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/myPostController")
public class MyPostController extends BasicController {
	
	@Autowired
	@Qualifier("myPostService")
	private MyPostService myPostService;
	
	@ModelAttribute
	@RequestMapping("/getMyPostList")
	public void getMyPostList(HttpServletRequest request,HttpServletResponse response,String postTitle,int pageIndex,int everyPageDataCount){
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			json.put("postAllNum", 0);
			json.put("allPage", 0);
			json.put("pageIndex", 0);
			json.put("postList", "");
			json.put("admin", ((String)session.getAttribute("admin")));
			String userUUID=(String) session.getAttribute("id");
			int postAllNum=myPostService.getMyPostAllNum(postTitle,userUUID);
			json.put("postAllNum", postAllNum);
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
				json.put("allPage", allPage);
				json.put("pageIndex", pageIndex);
				List<PostDto> list=myPostService.selectMyPostList(postTitle, pageIndex*everyPageDataCount , everyPageDataCount,userUUID);
				json.put("postList", list);
			}
		}
		
		this.writeJson(json.toString(), response);
	}

}
