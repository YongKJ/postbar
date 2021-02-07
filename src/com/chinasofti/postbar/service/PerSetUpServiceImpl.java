package com.chinasofti.postbar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.mapper.PbPerSetUpMapper;

@Service("perSetUpService")
public class PerSetUpServiceImpl implements PerSetUpService {
	
	@Autowired
	private PbPerSetUpMapper pbPerSetUpMapper;
	
	@Override
	public RegisterDto getRegisterDtoByUserUUID(String userUUID) {
		return pbPerSetUpMapper.getRegisterDtoByUserUUID(userUUID);
	}
	
	@Override
	public UserDto getUserDtoDtoByUserUUID(String userUUID) {
		return pbPerSetUpMapper.getUserDtoDtoByUserUUID(userUUID);
	}
	
	@Override
	public void UpdateRegisterDtoByRegUUID(String regUUID, String regSex, int regAge, String regEmial) {
		pbPerSetUpMapper.UpdateRegisterDtoByRegUUID(regUUID, regSex, regAge, regEmial);
	}
	
	@Override
	public void UpdateUserDtoByRegUUID(String userUUID, String userName) {
		pbPerSetUpMapper.UpdateUserDtoByRegUUID(userUUID, userName);
	}

}
