package com.chinasofti.postbar.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.aip.speech.TtsResponse;
import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.AppDto;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.service.PostService;
import com.chinasofti.postbar.util.AudioSynthesis;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/postController")
public class AddPostController extends BasicController {
	
	@Autowired
	@Qualifier("postService")
	private PostService postService;
	
	@ModelAttribute
	@RequestMapping("/addPost")
	
	public void addPost(HttpServletResponse response,HttpServletRequest request,String postTitle,String postText) {
		
		HttpSession session=request.getSession();
		JSONObject json=new JSONObject();
		json.put("message", "");
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			String postUUID=this.getUUID();
			String userUUID=(String) session.getAttribute("id");
			String postTime=this.getStringDate(new Date());
			int postPageviews=0;
			PostDto postDto=new PostDto();
			postDto.setPostPageviews(postPageviews);
			postDto.setPostText(postText);
			postDto.setPostTime(postTime);
			postDto.setPostTitle(postTitle);
			postDto.setPostUUID(postUUID);
			postDto.setUserUUID(userUUID);
		
			//---开始调用语音合成接口，将文字转换为mp3文件
			// 确定文本长度是否大于1024，大于截断字符串
			String text=this.removeHtmlTag(postText);
			if(text.length()>1024){
				text=text.substring(0,1024);
			}
			// 获取到当前用户下的语音配置信息
			AudioDto audioDto =postService.selectAudioByUserUUID(userUUID);
			int spd=audioDto.getAuSetSpd();
			int pit=audioDto.getAuSetPit();
			int vol=audioDto.getAuSetVol();
			int per=audioDto.getAuSetVoiPer();
			// 获取从百度AI开放平台获取的三个重要的KEY
			AppDto appDto=postService.selectApp();
			String APP_ID=appDto.getAppID();
			String API_KEY=appDto.getApiKey();
			String SECRET_KEY=appDto.getSecretKey();
			// 创建语音合成对象
			AudioSynthesis audioSynthesis=AudioSynthesis.getInstance(APP_ID, API_KEY, SECRET_KEY);
			// 配置语音合成对象
			TtsResponse res=audioSynthesis.synthesis(text, spd, pit, vol, per);
			// 获得从服务器返回的数据
			byte[] data = res.getData();
			// 设置存入硬盘的目录
			String path=request.getServletContext().getRealPath("/")+"audio/" ;
		    // 设置存入数据库的路径名
	        String urlPath=request.getContextPath() + "/audio/"+postUUID+".mp3";
	        // 将从服务器返回的数据存入硬盘中
	        File uploadDir = new File(path);
	        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
				uploadDir.mkdirs();
			}
	        path+=postUUID+".mp3";
			OutputStream out = null;
	        InputStream is = null;
			try {
				out = new FileOutputStream(path);
				is = new ByteArrayInputStream(data);
				byte[] buff = new byte[1024];
		        int len = 0;
		        while((len=is.read(buff))!=-1){
		            out.write(buff, 0, len);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(out!=null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			postDto.setPostAudio(urlPath);
			postService.addPost(postDto);
		}
		this.writeJson(json.toString(), response);
	}

}
