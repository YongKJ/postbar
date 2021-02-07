package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.PostPraiseDto;

public interface PbPostPraiseMapper {
	
	/**-根据postUUID查询文章点赞数*/
	@Select("SELECT * FROM pb_post_praise WHERE postUUID=#{postUUID}")
	List<PostPraiseDto> selectPraiseByPostUUID(@Param("postUUID") String postUUID);
	
	@Insert("INSERT INTO pb_post_praise (poPrUUID, postUUID, userUUID) VALUES (#{poPrUUID},#{postUUID},#{userUUID})")
	void addPostPraiseDto(PostPraiseDto postPraiseDto);

}
