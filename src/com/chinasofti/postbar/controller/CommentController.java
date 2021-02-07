package com.chinasofti.postbar.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
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
import com.chinasofti.postbar.dto.CommentPraiseDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostPraiseDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.service.CommentService;
import com.chinasofti.postbar.service.MyCommentService;
import com.chinasofti.postbar.service.PostService;
import com.chinasofti.postbar.util.AudioSynthesis;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/commentController")
public class CommentController extends BasicController {
	
	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("postService")
	private PostService postService;
	
	@Autowired
	@Qualifier("myCommentService")
	private MyCommentService myCommentService;
	
	@ModelAttribute
	@RequestMapping("/getInit")
	public void getInit(HttpServletResponse response,HttpServletRequest request,String postUUID) {
		HttpSession session=request.getSession();
		JSONObject json=new JSONObject();
		json.put("message", "");
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			PostDto postDto=commentService.selectPostByPostUUID(postUUID);// 查询文章信息
			json.put("post", postDto);
			UserDto userDto=commentService.selectUserByUserUUID(postDto.getUserUUID());// 查询用户信息
			json.put("user", userDto);
			RegisterDto registerDto=commentService.selectRegister(postDto.getUserUUID());// 查询用户注册信息
			json.put("register", registerDto);
			List<PostPraiseDto> list= commentService.selectPraiseByPostUUID(postUUID);// 查询点赞信息
			json.put("postPraise", list);
			commentService.updatePostPageviews(postUUID, postDto.getPostPageviews()+1);// 更新阅读人数
			String myUserUUID=(String) session.getAttribute("id"); // 从session中获得当前用户UUID
			json.put("myUserUUID", myUserUUID);
			String myAdmin=(String) session.getAttribute("admin");// 从session获得当前用户的权限级别
			json.put("myAdmin", myAdmin);
//			List<CommentDto> hotsCommentlist=commentService.selectCommentHotsByPostUUID(postUUID); // 获得热门评论
//			json.put("hotsCommentlist", hotsCommentlist);
			List<CommentDto> allCommentlist=commentService.selectAllCommentByPostUUID(postUUID); // 获得所有评论
			
			List<UserDto> userDtoList = commentService.selectAllUserDto();
			List<CommentPraiseDto> commentPraiseDtoList = commentService.selectAllCommentPraiseDto();
			List<RegisterDto> registerDtoList = commentService.selectAllRegisterDto();
			
			for(int i = 0; i < allCommentlist.size(); i++) {
				String cmUUID = allCommentlist.get(i).getCmUUID();
				int cmPrNum = 0;
				for(int j = 0; j < commentPraiseDtoList.size(); j++) {
					if(commentPraiseDtoList.get(j).getCmUUID().equals(cmUUID)) {
						cmPrNum++;
					}
				}
				allCommentlist.get(i).setCmPrNum(String.valueOf(cmPrNum));
				
				String userUUID = allCommentlist.get(i).getUserUUID();
				for(int j = 0; j < userDtoList.size(); j++) {
					if(userDtoList.get(j).getUserUUID().equals(userUUID)) {
						String userName = userDtoList.get(j).getUserName();
						allCommentlist.get(i).setUserName(userName);
						break;
					}
				}
				
				for(int j = 0; j < registerDtoList.size(); j++) {
					if(registerDtoList.get(j).getUserUUID().equals(userUUID)) {
						String regTime = registerDtoList.get(j).getRegTime();
						allCommentlist.get(i).setRegTime(regTime);
						String regPhoto = registerDtoList.get(j).getRegPhoto();
						allCommentlist.get(i).setRegPhoto(regPhoto);
						break;
					}
				}
			}
			
			json.put("allCommentlist", allCommentlist);
		}
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping("/addCom")
	public void addCom(HttpServletResponse response,HttpServletRequest request,String cmText,String postUUID) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		
		HttpSession session=request.getSession();
		CommentDto commentDto = new CommentDto();
		
		String cmUUID = this.getUUID();
		String userUUID=(String) session.getAttribute("id");
		String cmTime=this.getStringDate(new Date());
		
		//---开始调用语音合成接口，将文字转换为mp3文件
		// 确定文本长度是否大于1024，大于截断字符串
		String text=this.removeHtmlTag(cmText);
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
        String cmAudio=request.getContextPath() + "/audio/"+cmUUID+".mp3";
        // 将从服务器返回的数据存入硬盘中
        File uploadDir = new File(path);
        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
			uploadDir.mkdirs();
		}
        path+=cmUUID+".mp3";
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
		
		commentDto.setCmUUID(cmUUID);
		commentDto.setPostUUID(postUUID);
		commentDto.setUserUUID(userUUID);
		commentDto.setCmText(cmText);
		commentDto.setCmAudio(cmAudio);
		commentDto.setCmTime(cmTime);
		postService.addCommentDto(commentDto);
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@ModelAttribute
	@RequestMapping("/commentPostPraise")
	public void commentPostPraise(HttpServletResponse response,HttpServletRequest request,String postUUID) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		
		PostPraiseDto postPraiseDto = new PostPraiseDto();
		String poPrUUID = this.getUUID();
		HttpSession session=request.getSession();
		String userUUID=(String) session.getAttribute("id");
		
		postPraiseDto.setPoPrUUID(poPrUUID);
		postPraiseDto.setPostUUID(postUUID);
		postPraiseDto.setUserUUID(userUUID);
		
		postService.addPostPraiseDto(postPraiseDto);
		
		List<PostPraiseDto> list= commentService.selectPraiseByPostUUID(postUUID);// 查询点赞信息
		json.put("praiseNum", list.size());
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@ModelAttribute
	@RequestMapping("/commentPraise")
	public void commentPraise(HttpServletResponse response,HttpServletRequest request,String postUUID,String cmUUID) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		json.put("praiseMessage", ""); // 在对象中先添加入一个数据
		
		HttpSession session=request.getSession();
		String userUUID=(String) session.getAttribute("id");
		String cmPrUUID = this.getUUID();
		

		List<CommentPraiseDto> commentPraiseDtoList = commentService.selectAllCommentPraiseDto();
		boolean flag = false;
		for(int i = 0; i < commentPraiseDtoList.size(); i++) {
			if(commentPraiseDtoList.get(i).getPostUUID().equals(postUUID) && commentPraiseDtoList.get(i).getCmUUID().equals(cmUUID) && commentPraiseDtoList.get(i).getUserUUID().equals(userUUID)) {
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			CommentPraiseDto commentPraiseDto = new CommentPraiseDto();
			commentPraiseDto.setCmPrUUID(cmPrUUID);
			commentPraiseDto.setPostUUID(postUUID);
			commentPraiseDto.setUserUUID(userUUID);
			commentPraiseDto.setCmUUID(cmUUID);
			commentService.addCommentPraiseDto(commentPraiseDto);
			commentPraiseDtoList = commentService.selectAllCommentPraiseDto();
			json.put("pr", "");
		}else {

			json.put("pr", "1");
		}
		
		String myAdmin=(String) session.getAttribute("admin");// 从session获得当前用户的权限级别
		json.put("myAdmin", myAdmin);
		
		List<CommentDto> allCommentlist=commentService.selectAllCommentByPostUUID(postUUID); // 获得所有评论
		List<UserDto> userDtoList = commentService.selectAllUserDto();
		List<RegisterDto> registerDtoList = commentService.selectAllRegisterDto();
		
		for(int i = 0; i < allCommentlist.size(); i++) {
			String cmUUID1 = allCommentlist.get(i).getCmUUID();
			int cmPrNum = 0;
			for(int j = 0; j < commentPraiseDtoList.size(); j++) {
				if(commentPraiseDtoList.get(j).getCmUUID().equals(cmUUID1)) {
					cmPrNum++;
				}
			}
			allCommentlist.get(i).setCmPrNum(String.valueOf(cmPrNum));
			
			String userUUID1 = allCommentlist.get(i).getUserUUID();
			for(int j = 0; j < userDtoList.size(); j++) {
				if(userDtoList.get(j).getUserUUID().equals(userUUID1)) {
					String userName = userDtoList.get(j).getUserName();
					allCommentlist.get(i).setUserName(userName);
					break;
				}
			}
			
			for(int j = 0; j < registerDtoList.size(); j++) {
				if(registerDtoList.get(j).getUserUUID().equals(userUUID1)) {
					String regTime = registerDtoList.get(j).getRegTime();
					allCommentlist.get(i).setRegTime(regTime);
					String regPhoto = registerDtoList.get(j).getRegPhoto();
					allCommentlist.get(i).setRegPhoto(regPhoto);
					break;
				}
			}
		}
		
		json.put("allCommentlist", allCommentlist);
		
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@ModelAttribute
	@RequestMapping("/deleteComment")
	public void deleteComment(HttpServletResponse response,HttpServletRequest request,String cmUUID) {
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			cmUUID = cmUUID.substring(0, cmUUID.length() - 1);
			String[] cmUUIDs = cmUUID.split(",");
			
			for(int i = 0; i < cmUUIDs.length; i++) {
				
				String cmAudio = myCommentService.getCmAudioByCmUUID(cmUUIDs[i]);
				
				if(cmAudio != null && cmAudio.indexOf("mp3") != -1) {
					File file = new File(cmAudio);
			        if(file.isFile() && file.exists()) {
			        	file.delete();
			        }
				}
		        
		        myCommentService.delCommentDtoByCmUUID(cmUUIDs[i]);
				myCommentService.delCommentPraiseDtoByCmUUID(cmUUIDs[i]);
			}
		}
		
		this.writeJson(json.toString(), response);
	}

}
