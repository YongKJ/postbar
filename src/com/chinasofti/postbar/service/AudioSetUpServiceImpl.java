package com.chinasofti.postbar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.mapper.PbAudioSetUpMapper;

@Service("audioSetUpService")
public class AudioSetUpServiceImpl implements AudioSetUpService {
	
	@Autowired
	private PbAudioSetUpMapper pbAudioSetUpMapper;
	
	@Override
	public AudioDto getAudioDtoByUserUUID(String userUUID) {
		return pbAudioSetUpMapper.getAudioDtoByUserUUID(userUUID);
	}
	
	@Override
	public void updateAudioDtoByAuSetUUID(String auSetUUID, int auSetVoiPer, int auSetSpd, int auSetPit, int auSetVol) {
		pbAudioSetUpMapper.updateAudioDtoByAuSetUUID(auSetUUID, auSetVoiPer, auSetSpd, auSetPit, auSetVol);
	}
	
}
