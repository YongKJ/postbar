package com.chinasofti.postbar.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.service.UpLoadHeadService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/upLoadHeadController")
public class UpLoadHeadController extends BasicController {

	@Autowired
	@Qualifier("upLoadHeadService")
	private UpLoadHeadService upLoadHeadService;
	
	@ModelAttribute
	@RequestMapping("/selectHeadURL")
	public void selectHeadURL(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			String userUUID=(String) session.getAttribute("id");
			String headURL = upLoadHeadService.getHeadURLByUserUUID(userUUID);
			json.put("headURL", headURL);
		}
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping("/editHead")
	public void editHead(HttpServletRequest request,HttpServletResponse response, String file) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();// 创建一个json对象，用于返回到页面
		json.put("message", "");// 在json对象中添加一个返回错误的数据
		
		if(sessionTimeout(request)){
			json.put("message", "页面过期，请重新登录");
		}else{
			
			String userUUID=(String) session.getAttribute("id");
			String userName = upLoadHeadService.SelectUserNameByUserUUID(userUUID);
	        
	     // Base64解码
            byte[] data = Base64Utils.decodeFromString(file);
            for (int i = 0; i < data.length; ++i) {
                if (data[i] < 0) {// 调整异常数据
                	data[i] += 256;
                }
            }
            
         // 设置存入硬盘的目录
	     	String path=request.getServletContext().getRealPath("/")+"headPhoto/" + userName + "/" ;
	        // 将从服务器返回的数据存入硬盘中
	        File uploadDir = new File(path);
	        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
				uploadDir.mkdirs();
			}
         
		    // 设置存入数据库的路径名
	        String regPhoto=request.getContextPath() + "/headPhoto/" + userName + "/newPhoto.jpg";
	        if(regPhoto != null && regPhoto.indexOf("jpg") != -1) {
				File pictureFile = new File(regPhoto);
		        if(pictureFile.isFile() && pictureFile.exists()) {
		        	pictureFile.delete();
		        }
			}
	        
	        path+="newPhoto.jpg";
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

			upLoadHeadService.UpdateRegPhotoByUserUUID(userUUID, regPhoto);
			
		}
		
		this.writeJson(json.toString(), response);
	}
	
}
