package com.chinasofti.postbar.service;

import java.util.List;

import com.chinasofti.postbar.dto.AppDto;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostPraiseDto;

public interface PostService {
	
	List<PostDto> selectPostList(String postTitle, Integer pageNo, Integer pageSize, String userUUID);
	int getPostAllNum(String postTitle,String userUUID);
	void addPost(PostDto postDto);
	AudioDto selectAudioByUserUUID(String userUUID);
	AppDto selectApp();
	void addCommentDto(CommentDto commentDto);
	void addPostPraiseDto(PostPraiseDto postPraiseDto);
	void delPostDtoByPostUUID(String postUUID);
	void delPostPraiseDtoByPostUUID(String postUUID);
	String getPostAudioByPostUUID(String postUUID);
	
}
