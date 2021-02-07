package com.chinasofti.postbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommentPraiseDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostPraiseDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.mapper.PbCommentMapper;
import com.chinasofti.postbar.mapper.PbPostMapper;
import com.chinasofti.postbar.mapper.PbPostPraiseMapper;
import com.chinasofti.postbar.mapper.PbRegisterMapper;
import com.chinasofti.postbar.mapper.PbUserMapper;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PbPostMapper pbPostMapper;
	@Autowired
	private PbRegisterMapper pbRegisterMapper;
	@Autowired
	private PbUserMapper pbUserMapper;
	@Autowired
	private PbPostPraiseMapper pbPostPraiseMapper;
	@Autowired
	private PbCommentMapper pbCommentMapper;
	
	@Override
	public PostDto selectPostByPostUUID(String postUUID) {
		return pbPostMapper.selectPostByPostUUID(postUUID);
	}
	
	@Override
	public RegisterDto selectRegister(String userUUID) {
		return pbRegisterMapper.selectRegister(userUUID);
	}
	
	@Override
	public UserDto selectUserByUserUUID(String userUUID) {
		return pbUserMapper.selectUserByUserUUID(userUUID);
	}
	
	@Override
	public List<PostPraiseDto> selectPraiseByPostUUID(String postUUID) {
		return pbPostPraiseMapper.selectPraiseByPostUUID(postUUID);
	}
	
	@Override
	public void updatePostPageviews(String postUUID, int postPageviews) {
		pbPostMapper.updatePostPageviews(postUUID, postPageviews);
	}
	
	@Override
	public List<CommentDto> selectAllCommentByPostUUID(String postUUID) {
		return pbCommentMapper.selectAllCommentByPostUUID(postUUID);
	}
	
	@Override
	public List<UserDto> selectAllUserDto() {
		return pbCommentMapper.selectAllUserDto();
	}
	
	@Override
	public List<CommentPraiseDto> selectAllCommentPraiseDto() {
		return pbCommentMapper.selectAllCommentPraiseDto();
	}
	
	@Override
	public List<RegisterDto> selectAllRegisterDto() {
		return pbCommentMapper.selectAllRegisterDto();
	}
	
	@Override
	public void addCommentPraiseDto(CommentPraiseDto commentPraiseDto) {
		pbCommentMapper.addCommentPraiseDto(commentPraiseDto);
	}

}
