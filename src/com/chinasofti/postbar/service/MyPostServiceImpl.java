package com.chinasofti.postbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.mapper.PbMyPostMapper;

@Service("myPostService")
public class MyPostServiceImpl implements MyPostService {
	
	@Autowired
	private PbMyPostMapper pbMyPostMapper;
	
	@Override
	public List<PostDto> selectMyPostList(String postTitle, Integer pageNo, Integer pageSize, String userUUID) {
		return pbMyPostMapper.selectMyPostList(postTitle, pageNo, pageSize, userUUID);
	}

	
	@Override
	public int getMyPostAllNum(String postTitle,String userUUID) {
		return pbMyPostMapper.selectMyPostAllNum(postTitle,userUUID);
	}
	
}
