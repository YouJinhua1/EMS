package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.Major;
import com.yjh.domain.Team;
import com.yjh.mapper.TeamMapper;
import com.yjh.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Resource
	TeamMapper teamMapper;
	
	@Override
	public List<Team> selectTeamList() {
		return teamMapper.selectTeamList();
	}

	@Override
	public int findTeamByNameAndMajorId(String name, Integer majorId,String grade) {
		return teamMapper.findTeamByNameAndMajorId(name,majorId,grade);
	}

	@Override
	public int saveTeam(Team team) {
		return teamMapper.insertSelective(team);
	}

	@Override
	public List<Team> queryTeamByName(String name) {
		return teamMapper.queryTeamByName(name);
	}

	@Override
	public List<Team> queryTeamByMajorId(Integer sdeptId) {
		return teamMapper.queryTeamByMajorId(sdeptId);
	}

	@Override
	public int getStudentNumber(Integer teamId) {
		return teamMapper.getStudentNumber(teamId);
	}

	@Override
	public Team selectTeamById(Integer data) {
		return teamMapper.selectByPrimaryKey(data);
	}

	@Override
	public int updateTeam(Team team) {
		return teamMapper.updateByPrimaryKeySelective(team);
	}

	@Override
	public int deleteTeamById(Integer teamId) {
		return teamMapper.deleteByPrimaryKey(teamId);
	}

	@Override
	public List<Team> loadTeam(Integer majorId) {
		return teamMapper.queryTeamByMajorId(majorId);
	}
	

}
