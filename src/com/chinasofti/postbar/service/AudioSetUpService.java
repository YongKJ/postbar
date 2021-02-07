package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.AudioDto;

public interface AudioSetUpService {

	AudioDto getAudioDtoByUserUUID(String userUUID);
	void updateAudioDtoByAuSetUUID(String auSetUUID, int auSetVoiPer, int auSetSpd, int auSetPit, int auSetVol);
	
}
