package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface LoginService {
	
	UserDto getUserByUserNameAndPassword(String userName, String password);
	void addRegister(RegisterDto registerDto);
	void addUser(UserDto userDto);
	void addAudioDto(AudioDto audioDto);
	void updateUserPassword(String userUUID, String oldPassword, String newPassword);

}
