package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.AudioDto;

public interface PbAudioSetUpMapper {
	
	@Select("SELECT * FROM pb_audio WHERE userUUID=#{userUUID}")
	AudioDto getAudioDtoByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_audio SET auSetVoiPer = #{auSetVoiPer}, auSetSpd = #{auSetSpd}, auSetPit = #{auSetPit}, auSetVol = #{auSetVol} WHERE  auSetUUID = #{auSetUUID}")
	void updateAudioDtoByAuSetUUID(@Param("auSetUUID") String auSetUUID, @Param("auSetVoiPer") int auSetVoiPer, @Param("auSetSpd") int auSetSpd, @Param("auSetPit") int auSetPit, @Param("auSetVol") int auSetVol);

}
