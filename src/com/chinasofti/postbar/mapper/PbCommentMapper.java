package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommentPraiseDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface PbCommentMapper {
	
	@Select("SELECT * FROM pb_comment WHERE postUUID=#{postUUID}")
	List<CommentDto> selectAllCommentByPostUUID(@Param("postUUID") String postUUID);
	
	@Select("SELECT * FROM pb_user")
	List<UserDto> selectAllUserDto();
	
	@Select("SELECT * FROM pb_comment_praise")
	List<CommentPraiseDto> selectAllCommentPraiseDto();
	
	@Select("SELECT * FROM pb_register")
	List<RegisterDto> selectAllRegisterDto();

	@Insert("INSERT INTO pb_comment (cmUUID, postUUID, userUUID, cmText, cmAudio, cmTime) VALUES (#{cmUUID},#{postUUID},#{userUUID},#{cmText},#{cmAudio},#{cmTime})")
	void addCommentDto(CommentDto commentDto);
	
	@Insert("INSERT INTO pb_comment_praise (cmPrUUID, postUUID, userUUID, cmUUID) VALUES (#{cmPrUUID},#{postUUID},#{userUUID},#{cmUUID})")
	void addCommentPraiseDto(CommentPraiseDto commentPraiseDto);
}
