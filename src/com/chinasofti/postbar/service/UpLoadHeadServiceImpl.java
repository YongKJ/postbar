package com.chinasofti.postbar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.mapper.PbUpLoadHeadMapper;

@Service("upLoadHeadService")
public class UpLoadHeadServiceImpl implements UpLoadHeadService {
	
	@Autowired
	private PbUpLoadHeadMapper pbUpLoadHeadMapper;
	
	@Override
	public String getHeadURLByUserUUID(String userUUID) {
		return pbUpLoadHeadMapper.getHeadURLByUserUUID(userUUID);
	}
	
	@Override
	public void UpdateRegPhotoByUserUUID(String userUUID, String regPhoto) {
		pbUpLoadHeadMapper.UpdateRegPhotoByUserUUID(userUUID, regPhoto);
	}
	
	@Override
	public String SelectUserNameByUserUUID(String userUUID) {
		return pbUpLoadHeadMapper.SelectUserNameByUserUUID(userUUID);
	}

}
