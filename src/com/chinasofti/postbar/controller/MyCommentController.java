package com.chinasofti.postbar.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.baidu.aip.speech.TtsResponse;
import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.AppDto;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.service.MyCommentService;
import com.chinasofti.postbar.service.PostService;
import com.chinasofti.postbar.util.AudioSynthesis;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/myCommentController")
public class MyCommentController extends BasicController {
	
	@Autowired
	@Qualifier("myCommentService")
	private MyCommentService myCommentService;
	
	@Autowired
	@Qualifier("postService")
	private PostService postService;
	
	@ModelAttribute
	@RequestMapping("/selectMyCommentByUserUUID")
	public void commentPostPraise(HttpServletResponse response,HttpServletRequest request,int pageIndex, int everyPageDataCount) {
		
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
			
			List<CommentDto> commentDtoList = myCommentService.getAllCommentDto();
			List<CommentDto> myCommentDtoList = new ArrayList<CommentDto>();
			int postAllNum = 0;
			for(int i = 0; i < commentDtoList.size(); i++) {
				if(commentDtoList.get(i).getUserUUID().equals(userUUID)) {
					myCommentDtoList.add(commentDtoList.get(i));
					postAllNum++;
				}
			}
			
			List<PostDto> postDtoList = myCommentService.getAllPostDto();
			for(int i = 0; i < myCommentDtoList.size(); i++) {
				String postUUID = myCommentDtoList.get(i).getPostUUID();
				for(int j = 0; j < postDtoList.size(); j++) {
					if(postDtoList.get(j).getPostUUID().equals(postUUID)) {
						String postTitle = postDtoList.get(j).getPostTitle();
						myCommentDtoList.get(i).setPostTitle(postTitle);
						break;
					}
				}
			}
			
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
				
				List<CommentDto> theMyCommentDtoList = new ArrayList<CommentDto>();
				int a = pageIndex * everyPageDataCount;
				int b = a + everyPageDataCount > postAllNum ? postAllNum : a + everyPageDataCount;
				for(int i = a; i < b ; i++) {
					theMyCommentDtoList.add(myCommentDtoList.get(i));
				}
				
				json.put("allPage", allPage);
				json.put("pageIndex", pageIndex);
				json.put("myCommentlist", theMyCommentDtoList);
			}
		}
		
		this.writeJson(json.toString(), response);
		
	}
	
	@ModelAttribute
	@RequestMapping("/getCommentByCmUUID")
	public void getCommentByCmUUID(HttpServletResponse response,HttpServletRequest request,String cmUUID) {
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
//		if(sessionTimeout(request)){
//			json.put("message", "页面过期，请重新登录");
//		}else{
			String cmText = myCommentService.getCmTextByCmUUID(cmUUID);
			json.put("cmText", cmText);
//		}
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping("/editCom")
	public void editCom(HttpServletResponse response,HttpServletRequest request,String cmText, String cmUUID) {
		HttpSession session=request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			//---开始调用语音合成接口，将文字转换为mp3文件
			// 确定文本长度是否大于1024，大于截断字符串
			String text=this.removeHtmlTag(cmText);
			if(text.length()>1024){
				text=text.substring(0,1024);
			}
			// 获取到当前用户下的语音配置信息
			String userUUID=(String) session.getAttribute("id");
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
	        String cmAudio=request.getContextPath() + "/audio/"+cmUUID+".mp3";
	        
	        File file = new File(cmAudio);
	        if(file.isFile() && file.exists()) {
	        	file.delete();
	        }
	        
	        String audioUUID = this.getUUID();
	        cmAudio=request.getContextPath() + "/audio/"+audioUUID+".mp3";
	        
	        // 将从服务器返回的数据存入硬盘中
	        File uploadDir = new File(path);
	        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
				uploadDir.mkdirs();
			}
	        path+=audioUUID+".mp3";
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
			myCommentService.updateCmTextAndCmAudioByCmUUID(cmText, cmAudio, cmUUID);
		}
		
		this.writeJson(json.toString(), response);
	}
	
}
