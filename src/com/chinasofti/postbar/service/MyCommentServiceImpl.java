package com.chinasofti.postbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.mapper.PbMyCommentMapper;

@Service("myCommentService")
public class MyCommentServiceImpl implements MyCommentService {
	
	@Autowired
	private PbMyCommentMapper pbMyCommentMapper;
	
	@Override
	public List<CommentDto> getAllCommentDto() {
		return pbMyCommentMapper.getAllCommentDto();
	}
	
	@Override
	public List<PostDto> getAllPostDto() {
		return pbMyCommentMapper.getAllPostDto();
	}
	
	@Override
	public String getCmTextByCmUUID(String cmUUID) {
		return pbMyCommentMapper.getCmTextByCmUUID(cmUUID);
	}
	
	@Override
	public void updateCmTextAndCmAudioByCmUUID(String cmText, String cmAudio, String cmUUID) {
		pbMyCommentMapper.updateCmTextAndCmAudioByCmUUID(cmText, cmAudio, cmUUID);
	}
	
	@Override
	public void delCommentDtoByCmUUID(String cmUUID) {
		pbMyCommentMapper.delCommentDtoByCmUUID(cmUUID);
	}
	
	@Override
	public void delCommentPraiseDtoByCmUUID(String cmUUID) {
		pbMyCommentMapper.delCommentPraiseDtoByCmUUID(cmUUID);
	}
	
	@Override
	public String getCmAudioByCmUUID(String cmUUID) {
		return pbMyCommentMapper.getCmAudioByCmUUID(cmUUID);
	}

}
