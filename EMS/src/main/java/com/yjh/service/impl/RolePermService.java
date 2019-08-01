package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.mapper.SysRolePermMapper;

@Service
public class RolePermService implements com.yjh.service.RolePermService {

	@Resource
	SysRolePermMapper rolePermMapper;
	
	@Override
	public List<Integer> getPermIdListByRoleIdList(List<Integer> roleIdList) {
		return rolePermMapper.getPermIdListByRoleIdList(roleIdList);
	}

}
