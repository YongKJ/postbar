package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.PostDto;

public interface PbMyCommentMapper {
	
	@Select("SELECT * FROM pb_comment")
	List<CommentDto> getAllCommentDto();
	
	@Select("SELECT * FROM pb_post")
	List<PostDto> getAllPostDto();
	
	@Select("SELECT cmText FROM pb_comment WHERE cmUUID = #{cmUUID}")
	String getCmTextByCmUUID(@Param("cmUUID") String cmUUID);
	
	@Update("UPDATE pb_comment SET cmText = #{cmText}, cmAudio = #{cmAudio} WHERE cmUUID = #{cmUUID}")
	void updateCmTextAndCmAudioByCmUUID(@Param("cmText") String cmText, @Param("cmAudio") String cmAudio, @Param("cmUUID") String cmUUID);

	@Delete("DELETE FROM pb_comment WHERE cmUUID=#{cmUUID}")
	void delCommentDtoByCmUUID(@Param("cmUUID") String cmUUID);
	
	@Delete("DELETE FROM pb_comment_praise WHERE cmUUID=#{cmUUID}")
	void delCommentPraiseDtoByCmUUID(@Param("cmUUID") String cmUUID);
	
	@Select("SELECT cmAudio FROM pb_comment WHERE cmUUID = #{cmUUID}")
	String getCmAudioByCmUUID(@Param("cmUUID") String cmUUID);
}
