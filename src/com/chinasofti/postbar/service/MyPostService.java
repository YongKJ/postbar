package com.chinasofti.postbar.service;

import java.util.List;

import com.chinasofti.postbar.dto.PostDto;

public interface MyPostService {
	
	int getMyPostAllNum(String postTitle,String userUUID);
	List<PostDto> selectMyPostList(String postTitle, Integer pageNo, Integer pageSize, String userUUID);
	
}
