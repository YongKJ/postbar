package com.chinasofti.postbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.mapper.PbUserManageMapper;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

	@Autowired
	private PbUserManageMapper pbUserManageMapper;
	
	@Override
	public List<RegisterDto> getAllRegisterDto() {
		return pbUserManageMapper.getAllRegisterDto();
	}
	
	@Override
	public List<UserDto> getAllUserDto() {
		return pbUserManageMapper.getAllUserDto();
	}
	
	@Override
	public void delUserDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delUserDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delRegisterDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delRegisterDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delAudioDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delAudioDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delPostDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delPostDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delPostPraiseDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delPostPraiseDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delCommentDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delCommentDtoByUserUUID(userUUID);
	}
	
	@Override
	public void delCommentPraiseDtoByUserUUID(String userUUID) {
		pbUserManageMapper.delCommentPraiseDtoByUserUUID(userUUID);
	}
	
}
