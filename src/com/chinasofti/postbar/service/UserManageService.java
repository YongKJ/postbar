package com.chinasofti.postbar.service;

import java.util.List;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface UserManageService {
	
	List<RegisterDto> getAllRegisterDto();
	List<UserDto> getAllUserDto();
	void delUserDtoByUserUUID(String userUUID);
	void delRegisterDtoByUserUUID(String userUUID);
	void delAudioDtoByUserUUID(String userUUID);
	void delPostDtoByUserUUID(String userUUID);
	void delPostPraiseDtoByUserUUID(String userUUID);
	void delCommentDtoByUserUUID(String userUUID);
	void delCommentPraiseDtoByUserUUID(String userUUID);

}
