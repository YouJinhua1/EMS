package com.yjh.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.MajorAdpter;
import com.yjh.adpter.SdeptAdpter;
import com.yjh.adpter.TeamAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.Major;
import com.yjh.domain.Sdept;
import com.yjh.domain.SysRole;
import com.yjh.domain.SysUser;
import com.yjh.domain.Team;
import com.yjh.service.MajorService;
import com.yjh.service.SdeptService;
import com.yjh.service.TeamService;
import com.yjh.service.UserService;


@Controller
@RequestMapping("/team")
public class TeamController {
	@Resource
	MajorService majorService;
	
	@Resource
	UserService userService;
	
	@Resource
	TeamService teamService;
	
	@RequiresPermissions("team:query")
	@RequestMapping("/toTeamPage")
	public String queryCourseList(Model model) {
		model.addAttribute("include","/admin/team");
		return "success";
	}
	
	@RequiresPermissions("team:query")
	@RequestMapping("/teamList")
	@ResponseBody
	public ResultData teamList() {
		//查出班级信息
		List<Team> teamList=teamService.selectTeamList();
		List<TeamAdpter> teamAdpterList=new ArrayList<TeamAdpter>();
		//查出每个专业领导人的名字,以及所属的院系名称
		for(Team team:teamList) {
			String teacherName=userService.selectUserNameById(team.getTeacherId());
			Major major=majorService.selectMajorById(team.getMajorId());
			TeamAdpter teamAdpter=TeamAdpter.getTeamAdpter(team);
			teamAdpter.setTeacherName(teacherName);
			teamAdpter.setMajorName(major.getName());
			teamAdpterList.add(teamAdpter);
		}
		return 	ResultData.success(teamAdpterList);
	}
	@RequiresPermissions("team:query")
	@RequestMapping("/loadTeacher")
	@ResponseBody
	public ResultData loadTeacher() {
		List<SysUser> leaderList=userService.getTeamTeacher();
		if(leaderList!=null&&leaderList.size()>0) {
			return ResultData.success(leaderList);
		}else {
			return ResultData.fail("加载教师数据失败！");
		}
	}
	@RequiresPermissions("team:query")
	@RequestMapping("/loadMajor")
	@ResponseBody
	public ResultData loadMajor() {
		List<Sdept> sdeptList=majorService.getMajorList();
		if(sdeptList!=null&&sdeptList.size()>0) {
			return ResultData.success(sdeptList);
		}else {
			return ResultData.fail("加载专业数据失败！");
		}
	}
	
	@RequiresPermissions("team:save")
	@RequestMapping("/saveTeam")
	@ResponseBody
	public ResultData saveMajor(Team team) {
		int count=teamService.findTeamByNameAndMajorId(team.getName(),team.getMajorId(),team.getGrade());
		if(count>0) {
			return ResultData.fail("添加失败，该班级名称在所属的专业中已经存在！");
		}
		int x=teamService.saveTeam(team);
		if(x>0) {
			return ResultData.success("添加成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	@RequiresPermissions("team:query")
	@RequestMapping("/queryTeamByMajorId")
	@ResponseBody
	public ResultData queryTeamByMajorId(@RequestParam("sdeptId")Integer sdeptId) {
		//查出专业信息
		List<Team> teamList=teamService.queryTeamByMajorId(sdeptId);
		if(!(teamList!=null&&teamList.size()>0)){
			return ResultData.fail("该专业暂时还没有任何班级！");
		}
		List<TeamAdpter> teamAdpterList=new ArrayList<TeamAdpter>();
		//查出每个专业领导人的名字,以及所属的院系名称
		for(Team team:teamList) {
			String teacherName=userService.selectUserNameById(team.getTeacherId());
			Major major=majorService.selectMajorById(team.getMajorId());
			TeamAdpter teamAdpter=TeamAdpter.getTeamAdpter(team);
			teamAdpter.setTeacherName(teacherName);
			teamAdpter.setMajorName(major.getName());
			teamAdpterList.add(teamAdpter);
		}
		return 	ResultData.success(teamAdpterList);
	}
	
	@RequiresPermissions("team:query")
	@RequestMapping("/queryTeamByName")
	@ResponseBody
	public ResultData queryTeamByName(@RequestParam("name") String name) {
		List<Team> teamList=teamService.queryTeamByName(name);
		if(!(teamList!=null&&teamList.size()>0)){
			return ResultData.fail("没有查到任何有关："+name+"的班级信息！");
		}
		List<TeamAdpter> teamAdpterList=new ArrayList<TeamAdpter>();
		for(Team team:teamList) {
			String teacherName=userService.selectUserNameById(team.getTeacherId());
			Major major=majorService.selectMajorById(team.getMajorId());
			TeamAdpter teamAdpter=TeamAdpter.getTeamAdpter(team);
			teamAdpter.setTeacherName(teacherName);
			teamAdpter.setMajorName(major.getName());
			teamAdpterList.add(teamAdpter);
		}
		return 	ResultData.success(teamAdpterList);
	}
	
	@RequiresPermissions("team:delete")
	@RequestMapping("/deleteTeam")
	@ResponseBody
	public ResultData deleteTeam(@RequestParam("id")Integer teamId) {
		int number=teamService.getStudentNumber(teamId);
		if(number==0) {
			int x=teamService.deleteTeamById(teamId);
			if(x>0) {
				return ResultData.success("删除成功！");
			}else {
				return ResultData.fail("删除失败！");
			}
		}else {
			return ResultData.fail("该班级下，还有学生不能删除！");
		}
		
	}
	
	@RequiresPermissions("team:update")
	@RequestMapping("/findTeamById")
	@ResponseBody
	public ResultData findTeamById(@RequestParam("data")Integer data) {
		Team team=teamService.selectTeamById(data);
		if(team!=null) {
			return ResultData.success(team);
		}else {
			return ResultData.fail("该班级已经不存在了！");
		}
	}
	
	@RequiresPermissions("team:update")
	@RequestMapping("/updateTeam")
	@ResponseBody
	public ResultData updateTeam(Team team) {
		int count=teamService.findTeamByNameAndMajorId(team.getName(),team.getMajorId(),team.getGrade());
		if(count>0) {
			return ResultData.fail("修改失败，该班级名称在所属的专业中已经存在！");
		}
		int x=teamService.updateTeam(team);
		if(x>0) {
			return ResultData.success("更新成功！");
		}else {
			return ResultData.fail("更新失败！");
		}
	}
	
	
}
