package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.SysRoleUser;

public interface SysRoleUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SysRoleUser record);

	int insertSelective(SysRoleUser record);

	SysRoleUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysRoleUser record);

	int updateByPrimaryKey(SysRoleUser record);

	// 根据用户id查询所有的角色id
	List<Integer> getRoleIdByUserId(Integer userId);

	void deleteByRoleId(@Param("roleId")int roleId);

	void changeRoleUser(@Param("roleUserList")List<SysRoleUser> roleUserList);

	int batchInsertRoleUserSelective(@Param("roleUserList")List<SysRoleUser> roleUserList);

	
}