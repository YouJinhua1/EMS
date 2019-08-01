package com.yjh.service;

import java.util.List;

import com.yjh.domain.SysRole;

public interface RoleService {
	
	List<SysRole> selectRoleList();
	
	int getRoleCountByStatus(Integer status);
	
	int getRoleCount();

	int saveRole(SysRole role);

	SysRole selectRoleById(Integer roleId);

	int deleteRoleById(Integer roleId);

	int updateRole(SysRole role);

	List<SysRole> queryRoleByStatus(int status);

	List<SysRole> queryRoleByName(String name);

}
