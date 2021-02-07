package com.chinasofti.postbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.AppDto;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostPraiseDto;
import com.chinasofti.postbar.mapper.PbAppMapper;
import com.chinasofti.postbar.mapper.PbAudioMapper;
import com.chinasofti.postbar.mapper.PbCommentMapper;
import com.chinasofti.postbar.mapper.PbPostMapper;
import com.chinasofti.postbar.mapper.PbPostPraiseMapper;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PbPostMapper pbPostMapper;
	@Autowired
	private PbAudioMapper pbAudioMapper;
	@Autowired
	private PbAppMapper pbAppMapper;
	@Autowired
	private PbCommentMapper pbCommentMapper;
	@Autowired
	private PbPostPraiseMapper pbPostPraiseMapper;
	
	@Override
	public List<PostDto> selectPostList(String postTitle, Integer pageNo, Integer pageSize, String userUUID) {
		return pbPostMapper.selectPostList(postTitle, pageNo, pageSize, userUUID);
	}
	
	@Override
	public int getPostAllNum(String postTitle,String userUUID) {
		return pbPostMapper.selectPostAllNum(postTitle,userUUID);
	}
	
	@Override
	public void addPost(PostDto postDto) {
		pbPostMapper.insertPost(postDto);
	}
	
	@Override
	public AudioDto selectAudioByUserUUID(String userUUID) {
		return pbAudioMapper.selectAudioByUserUUID(userUUID);
	}
	
	@Override
	public AppDto selectApp() {
		return pbAppMapper.selectApp();
	}
	
	@Override
	public void addCommentDto(CommentDto commentDto) {
		pbCommentMapper.addCommentDto(commentDto);
	}
	
	@Override
	public void addPostPraiseDto(PostPraiseDto postPraiseDto) {
		pbPostPraiseMapper.addPostPraiseDto(postPraiseDto);
	}
	
	@Override
	public void delPostDtoByPostUUID(String postUUID) {
		pbPostMapper.delPostDtoByPostUUID(postUUID);
	}
	
	@Override
	public void delPostPraiseDtoByPostUUID(String postUUID) {
		pbPostMapper.delPostPraiseDtoByPostUUID(postUUID);
	}
	
	@Override
	public String getPostAudioByPostUUID(String postUUID) {
		return pbPostMapper.getPostAudioByPostUUID(postUUID);
	}
	
}
