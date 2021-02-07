package com.chinasofti.postbar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.mapper.PbUserMapper;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PbUserMapper pbUserMapper;
	
	@Override
	public UserDto getUserByUserNameAndPassword(String userName, String password) {
		return pbUserMapper.selectUserByUserNameAndPassword(userName, password);
	}
	
	@Override
	public void addRegister(RegisterDto registerDto) {
		pbUserMapper.insertRegisterDto(registerDto);
	}
	
	@Override
	public void addUser(UserDto userDto) {
		pbUserMapper.insertUserDto(userDto);
	}
	
	@Override
	public void addAudioDto(AudioDto audioDto) {
		pbUserMapper.insertAudioDto(audioDto);
	}
	
	@Override
	public void updateUserPassword(String userUUID, String oldPassword, String newPassword) {
		pbUserMapper.updateUserPassword(userUUID, oldPassword, newPassword);
	}
	
}
