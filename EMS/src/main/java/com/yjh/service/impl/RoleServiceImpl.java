package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.SysRole;
import com.yjh.mapper.SysRoleMapper;
import com.yjh.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	SysRoleMapper roleMapper;

	@Override
	public List<SysRole> selectRoleList() {
		return roleMapper.selectRoleList();
	}
	
	@Override
	public int getRoleCountByStatus(Integer status) {
		return roleMapper.getRoleCountByStatus(status);
	}

	@Override
	public int getRoleCount() {
		return roleMapper.getRoleCount();
	}

	@Override
	public int saveRole(SysRole role) {
		return roleMapper.insertSelective(role);
	}

	@Override
	public SysRole selectRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int deleteRoleById(Integer roleId) {
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int updateRole(SysRole role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<SysRole> queryRoleByStatus(int status) {
		return roleMapper.queryRoleByStatus(status);
	}

	@Override
	public List<SysRole> queryRoleByName(String name) {
		return roleMapper.queryRoleByName(name);
	}

}
