package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser findUserByNumber(String number);

	List<SysUser> queryUser(@Param("name")String name);

	List<SysUser> queryUserByRoleId(@Param("roleId") int roleId);

	String selectUserNameById(@Param("userId")Integer leaderId);

	List<SysUser> getSdeptLeader();

	List<SysUser> getMajorLeader();

	List<SysUser> getTeacher();

	List<SysUser> selectTeacherList();

	List<SysUser> queryTeacherBySdeptId(@Param("sdeptId")Integer teacherId);

	List<SysUser> queryTeacherByName(@Param("name") String name);

	List<SysUser> selectStudentList();

	List<SysUser> queryStudentByMajorId(@Param("majorId")Integer majorId);

	List<SysUser> queryStudentByTeamId(@Param("teamId")Integer teamId);

	List<SysUser> queryStudentByName(@Param("name")String name);

	List<SysUser> queryStudentBySdeptId(@Param("sdeptId")Integer sdeptId);

	int batchInsertStudentSelective(@Param("students")List<SysUser> students);

	List<SysUser> selectStudentListByStudentIds(@Param("studentIds")List<Integer> studentIdList);
}