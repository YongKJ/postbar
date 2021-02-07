package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;

public interface PbUserMapper {
	
	@Select("SELECT * FROM pb_user WHERE userName=#{userName} and password=#{password}")
	UserDto selectUserByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);
	
	/**-根据userUUID查询用户信息*/
	@Select("SELECT * FROM pb_user WHERE userUUID=#{userUUID}")
	UserDto selectUserByUserUUID(@Param("userUUID") String userUUID);

	@Insert("INSERT INTO pb_register (regUUID, userUUID, regSex, regAge, regEmial, regPhoto, regTime) VALUES (#{regUUID},#{userUUID},#{regSex},#{regAge},#{regEmial},#{regPhoto},#{regTime})")
	void insertRegisterDto(RegisterDto registerDto);
	
	@Insert("INSERT INTO pb_user (userUUID, userName, password, loginTime, admin) VALUES (#{userUUID},#{userName},#{password},#{loginTime},#{admin})")
	void insertUserDto(UserDto userDto);
	
	@Insert("INSERT INTO pb_audio (auSetUUID, userUUID, auSetSpd, auSetPit, auSetVol, auSetVoiPer) VALUES (#{auSetUUID},#{userUUID},#{auSetSpd},#{auSetPit},#{auSetVol},#{auSetVoiPer})")
	void insertAudioDto(AudioDto audioDto);
	
	@Update("UPDATE pb_user SET password=#{newPassword} WHERE userUUID=#{userUUID} and password=#{oldPassword}")
	void updateUserPassword(@Param("userUUID") String userUUID, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
	
}
