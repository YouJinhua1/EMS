package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.mapper.SysRoleUserMapper;
import com.yjh.service.RoleUserService;
@Service
public class RoleUserServiceImpl implements RoleUserService{

	@Resource
	SysRoleUserMapper roleUserMapper;
	
	@Override
	public List<Integer> getRoleIdByUserId(Integer userId) {
		return roleUserMapper.getRoleIdByUserId(userId);
	}

}
