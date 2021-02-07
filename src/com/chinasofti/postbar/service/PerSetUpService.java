package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface PerSetUpService {
	
	RegisterDto getRegisterDtoByUserUUID(String userUUID);
	UserDto getUserDtoDtoByUserUUID(String userUUID);
	void UpdateRegisterDtoByRegUUID(String regUUID, String regSex, int regAge, String regEmial);
	void UpdateUserDtoByRegUUID(String userUUID, String userName);

}
