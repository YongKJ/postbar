package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.AppDto;

public interface PbAppMapper {
	
	/** 从字典表中查出网上申请的三个KEY */
	@Select("select id,appID,apiKey,secretKey from pb_app")
	AppDto selectApp();

}
