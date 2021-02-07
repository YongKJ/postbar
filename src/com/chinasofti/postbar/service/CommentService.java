package com.chinasofti.postbar.service;

import java.util.List;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommentPraiseDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostPraiseDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface CommentService {
	
	PostDto selectPostByPostUUID(String postUUID);
	RegisterDto selectRegister(String userUUID);
	UserDto selectUserByUserUUID(String userUUID);
	List<PostPraiseDto> selectPraiseByPostUUID(String postUUID);
	void updatePostPageviews(String postUUID, int postPageviews);
	List<CommentDto> selectAllCommentByPostUUID(String postUUID);
	List<UserDto> selectAllUserDto();
	List<CommentPraiseDto> selectAllCommentPraiseDto();
	List<RegisterDto> selectAllRegisterDto();
	void addCommentPraiseDto(CommentPraiseDto commentPraiseDto);

}
