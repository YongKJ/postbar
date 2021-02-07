package com.chinasofti.postbar.service;

import java.util.List;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;

public interface MyCommentService {

	List<CommentDto> getAllCommentDto();
	List<PostDto> getAllPostDto();
	String getCmTextByCmUUID(String cmUUID);
	void updateCmTextAndCmAudioByCmUUID(String cmText, String cmAudio, String cmUUID);
	void delCommentDtoByCmUUID(String cmUUID);
	void delCommentPraiseDtoByCmUUID(String cmUUID);
	String getCmAudioByCmUUID(String cmUUID);
	
}
