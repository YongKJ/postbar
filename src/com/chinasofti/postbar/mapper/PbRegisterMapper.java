package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.RegisterDto;

public interface PbRegisterMapper {
	
	/**-根据userUUID查询注册信息*/
	@Select("SELECT * FROM pb_register WHERE userUUID=#{userUUID}")
	RegisterDto selectRegister(@Param("userUUID") String userUUID);

}
