package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.AudioDto;

public interface PbAudioMapper {
	
	/** -根据UUID查询用户的语音配置信息 */
	@Select("SELECT auSetUUID,userUUID,auSetSpd,auSetPit,auSetVol,auSetVoiPer FROM pb_audio WHERE userUUID=#{userUUID}")
	AudioDto selectAudioByUserUUID(@Param("userUUID") String userUUID);

}
