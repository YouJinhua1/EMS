package com.yjh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yjh.domain.SysUser;

public interface UserService {
	public SysUser getUser();
	
	public void saveUser() ;
	
	public SysUser findUserByNumber(String number);

	public List<SysUser> queryUser(String name);

	public List<SysUser> queryUserByRoleId(int roleId);

	public String selectUserNameById(Integer leaderId);

	public List<SysUser> getSdeptLeader();

	public List<SysUser> getMajorLeader();
	
	public List<SysUser> getTeacher();

	public List<SysUser> getTeamTeacher();

	public List<SysUser> selectTeacherList();

	public int saveTeacher(SysUser teacher, HttpServletRequest request);

	public List<SysUser> queryTeacherBySdeptId(Integer sdeptId);

	public List<SysUser> queryTeacherByName(String name);

	public int deleteTeacherById(Integer teamId);

	public int updateTeacher(SysUser teacher);

	public SysUser findTeacherById(Integer data);

	public List<SysUser> selectStudentList();

	public List<SysUser> queryStudentByMajorId(Integer majorId);

	public List<SysUser> queryStudentByTeamId(Integer teamId);

	public List<SysUser> queryStudentByName(String name);

	public List<SysUser> queryStudentBySdeptId(Integer sdeptId);

	public int saveStudent(List<SysUser> students,HttpServletRequest request);

	public List<SysUser> selectStudentListByStudentIds(List<Integer> studentIdList);

}
