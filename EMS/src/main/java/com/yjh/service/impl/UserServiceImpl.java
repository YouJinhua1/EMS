package com.yjh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjh.domain.SysRoleUser;
import com.yjh.domain.SysUser;
import com.yjh.exception.ParamException;
import com.yjh.mapper.SysRoleMapper;
import com.yjh.mapper.SysRoleUserMapper;
import com.yjh.mapper.SysUserMapper;
import com.yjh.service.UserService;
import com.yjh.utils.IpUtil;

@Service
public class UserServiceImpl implements UserService{


	@Resource
	SysUserMapper sysUserMapper;
	
	@Resource
	SysRoleUserMapper roleUerMapper;
	
	@Resource 
	SysRoleMapper roleMapper;
	
	public SysUser getUser() {
		return sysUserMapper.selectByPrimaryKey(1);
	}
	
	//@Transactional
	public void saveUser() {
		SysUser user=SysUser.builder().name("lisi").age(23).entime(new Date()).gender("男").build();
		
		sysUserMapper.insertSelective(user);
		throw new ParamException("参数不合法");
	}

	//根据学号或者工号查询用户
	public SysUser findUserByNumber(String number) {
		return sysUserMapper.findUserByNumber(number);
	}
	
	@Override
	public List<SysUser> queryUser(String name) {
		return sysUserMapper.queryUser(name);
	}
	
	@Override
	public List<SysUser> queryUserByRoleId(int roleId) {
		return sysUserMapper.queryUserByRoleId(roleId);
	}
	
	@Override
	public String selectUserNameById(Integer leaderId) {
		return sysUserMapper.selectUserNameById(leaderId);
	}

	@Override
	public List<SysUser> getSdeptLeader() {
		return sysUserMapper.getSdeptLeader();
	}

	@Override
	public List<SysUser> getMajorLeader() {
		return sysUserMapper.getMajorLeader();
	}
	
	@Override
	public List<SysUser> getTeacher() {
		return sysUserMapper.getTeacher();
	}
	@Override
	public List<SysUser> getTeamTeacher() {
		return sysUserMapper.getTeacher();
	}

	@Override
	public List<SysUser> selectTeacherList() {
		return sysUserMapper.selectTeacherList();
	}

	@Override
	@Transactional
	public int saveTeacher(SysUser teacher, HttpServletRequest request) {
		sysUserMapper.insertSelective(teacher);
		SysRoleUser roleUser=new SysRoleUser();
		HttpSession session=request.getSession();
		SysUser user=(SysUser) session.getAttribute("user");
		roleUser.setOperator(user.getName());
		roleUser.setOperateIp(IpUtil.getRemoteIp(request));
		roleUser.setOperateTime(new Date());
		roleUser.setUserId(teacher.getId());
		roleUser.setRoleId(roleMapper.getRoleIdByName("教师"));
		return roleUerMapper.insertSelective(roleUser);
	}

	@Override
	public List<SysUser> queryTeacherBySdeptId(Integer teacherId) {
		return sysUserMapper.queryTeacherBySdeptId(teacherId);
	}

	@Override
	public List<SysUser> queryTeacherByName(String name) {
		return sysUserMapper.queryTeacherByName(name);
	}

	@Override
	public int deleteTeacherById(Integer teamId) {
		return sysUserMapper.deleteByPrimaryKey(teamId);
	}

	@Override
	public int updateTeacher(SysUser teacher) {
		return sysUserMapper.updateByPrimaryKeySelective(teacher);
	}

	@Override
	public SysUser findTeacherById(Integer data) {
		return sysUserMapper.selectByPrimaryKey(data);
	}

	@Override
	public List<SysUser> selectStudentList() {
		return sysUserMapper.selectStudentList();
	}

	@Override
	public List<SysUser> queryStudentByMajorId(Integer majorId) {
		return sysUserMapper.queryStudentByMajorId(majorId);
	}

	@Override
	public List<SysUser> queryStudentByTeamId(Integer teamId) {
		return sysUserMapper.queryStudentByTeamId(teamId);
	}

	@Override
	public List<SysUser> queryStudentByName(String name) {
		return sysUserMapper.queryStudentByName(name);
	}

	@Override
	public List<SysUser> queryStudentBySdeptId(Integer sdeptId) {
		return sysUserMapper.queryStudentBySdeptId(sdeptId);
	}
	
	@Override
	@Transactional
	public int saveStudent(List<SysUser> students, HttpServletRequest request) {
		sysUserMapper.batchInsertStudentSelective(students);
		HttpSession session=request.getSession();
		SysUser user=(SysUser) session.getAttribute("user");
		List<SysRoleUser> roleUserList=new ArrayList<SysRoleUser>(); 
		for(SysUser student:students) {
			SysRoleUser roleUser=new SysRoleUser();
			roleUser.setRoleId(roleMapper.getRoleIdByName("学生"));
			roleUser.setOperator(user.getName());
			roleUser.setOperateIp(IpUtil.getRemoteIp(request));
			roleUser.setOperateTime(new Date());
			roleUser.setUserId(student.getId());
			roleUserList.add(roleUser);
		}
		return roleUerMapper.batchInsertRoleUserSelective(roleUserList);
	}

	@Override
	public List<SysUser> selectStudentListByStudentIds(List<Integer> studentIdList) {
		return sysUserMapper.selectStudentListByStudentIds(studentIdList);
	}
	
	
}
