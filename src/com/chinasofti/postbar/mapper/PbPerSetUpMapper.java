package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface PbPerSetUpMapper {

	@Select("SELECT * FROM pb_register WHERE userUUID=#{userUUID}")
	RegisterDto getRegisterDtoByUserUUID(@Param("userUUID") String userUUID);
	
	@Select("SELECT * FROM pb_user WHERE userUUID=#{userUUID}")
	UserDto getUserDtoDtoByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_register SET regSex = #{regSex}, regAge = #{regAge}, regEmial = #{regEmial} WHERE regUUID = #{regUUID}")
	void UpdateRegisterDtoByRegUUID(@Param("regUUID") String regUUID, @Param("regSex") String regSex, @Param("regAge") int regAge, @Param("regEmial") String regEmial);
	
	@Update("UPDATE pb_user SET userName = #{userName} WHERE userUUID = #{userUUID}")
	void UpdateUserDtoByRegUUID(@Param("userUUID") String userUUID, @Param("userName") String userName);
	
}
