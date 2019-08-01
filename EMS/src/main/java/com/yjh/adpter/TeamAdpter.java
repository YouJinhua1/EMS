package com.yjh.adpter;

import org.springframework.beans.BeanUtils;

import com.yjh.domain.Team;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class TeamAdpter extends Team{
    private String majorName;
	
	private String teacherName;
	
	//提供一个静态方法将major转成sdeptAdpter
	public static TeamAdpter getTeamAdpter(Team team) {
		TeamAdpter teamAdpter=new TeamAdpter();
		BeanUtils.copyProperties(team, teamAdpter);
		return teamAdpter;
	}
}
