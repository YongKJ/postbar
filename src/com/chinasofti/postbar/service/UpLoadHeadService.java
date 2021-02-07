package com.chinasofti.postbar.service;

public interface UpLoadHeadService {
	
	String getHeadURLByUserUUID(String userUUID);
	void UpdateRegPhotoByUserUUID(String userUUID, String regPhoto);
	String SelectUserNameByUserUUID(String userUUID);

}
