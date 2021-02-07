package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PbUpLoadHeadMapper {
	
	@Select("SELECT regPhoto FROM pb_register WHERE userUUID=#{userUUID}")
	String getHeadURLByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_register SET regPhoto = #{regPhoto} WHERE userUUID = #{userUUID}")
	void UpdateRegPhotoByUserUUID(@Param("userUUID") String userUUID, @Param("regPhoto") String regPhoto);
	
	@Select("SELECT userName FROM pb_user WHERE userUUID = #{userUUID}")
	String SelectUserNameByUserUUID(@Param("userUUID")String userUUID);
}
