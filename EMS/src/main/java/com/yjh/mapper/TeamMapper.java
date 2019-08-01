package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.Team;

public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

	List<Team> selectTeamList();

	int findTeamByNameAndMajorId(@Param("name")String name, @Param("majorId")Integer majorId,@Param("grade")String grade);

	List<Team> queryTeamByName(@Param("name")String name);

	List<Team> queryTeamByMajorId(@Param("majorId")Integer majorId);

	int getStudentNumber(Integer id);
}