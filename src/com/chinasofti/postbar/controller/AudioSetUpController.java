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
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.service.AudioSetUpService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/audioSetUpController")
public class AudioSetUpController extends BasicController {

	@Autowired
	@Qualifier("audioSetUpService")
	private AudioSetUpService audioSetUpService;
	
	@ModelAttribute
	@RequestMapping("/selectAudioSetUp")
	public void getMyPostList(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		String userUUID=(String) session.getAttribute("id");
		AudioDto audioDto = audioSetUpService.getAudioDtoByUserUUID(userUUID);
		json.put("auidoDto", audioDto);
		
		this.writeJson(json.toString(), response);
		
	}
	
	@ModelAttribute
	@RequestMapping("/updateAudioSetUp")
	public void updateAudioSetUp(HttpServletRequest request,HttpServletResponse response, String auSetUUID, int auSetVoiPer, int auSetSpd, int auSetPit, int auSetVol) {
		
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		audioSetUpService.updateAudioDtoByAuSetUUID(auSetUUID, auSetVoiPer, auSetSpd, auSetPit, auSetVol);
		
		this.writeJson(json.toString(), response);
		
	}
	
}
