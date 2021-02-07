package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface PbUserManageMapper {
	
	@Select("SELECT * FROM pb_register")
	List<RegisterDto> getAllRegisterDto();
	
	@Select("SELECT * FROM pb_user")
	List<UserDto> getAllUserDto();
	
	@Delete("DELETE FROM pb_user WHERE userUUID = #{userUUID}")
	void delUserDtoByUserUUID(@Param("userUUID") String userUUID);
	
	@Delete("DELETE FROM pb_register WHERE userUUID = #{userUUID}")
	void delRegisterDtoByUserUUID(@Param("userUUID") String userUUID);
	
	@Delete("DELETE FROM pb_audio WHERE userUUID = #{userUUID}")
	void delAudioDtoByUserUUID(String userUUID);
	
	@Delete("DELETE FROM pb_post WHERE userUUID = #{userUUID}")
	void delPostDtoByUserUUID(String userUUID);
	
	@Delete("DELETE FROM pb_post_praise WHERE userUUID = #{userUUID}")
	void delPostPraiseDtoByUserUUID(String userUUID);
	
	@Delete("DELETE FROM pb_comment WHERE userUUID = #{userUUID}")
	void delCommentDtoByUserUUID(String userUUID);
	
	@Delete("DELETE FROM pb_comment_praise WHERE userUUID = #{userUUID}")
	void delCommentPraiseDtoByUserUUID(String userUUID);

}
