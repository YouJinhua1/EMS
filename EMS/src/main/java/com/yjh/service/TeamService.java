package com.yjh.service;

import java.util.List;

import com.yjh.domain.Major;
import com.yjh.domain.Team;

public interface TeamService {

	List<Team> selectTeamList();

	int findTeamByNameAndMajorId(String name, Integer majorId,String grade);

	int saveTeam(Team team);

	List<Team> queryTeamByName(String name);

	List<Team> queryTeamByMajorId(Integer sdeptId);

	int getStudentNumber(Integer teamId);

	Team selectTeamById(Integer data);

	int updateTeam(Team team);

	int deleteTeamById(Integer teamId);

	List<Team> loadTeam(Integer majorId);

}
