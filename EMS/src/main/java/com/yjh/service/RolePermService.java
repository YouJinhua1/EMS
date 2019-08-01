package com.yjh.service;

import java.util.List;

import com.yjh.domain.SysPerm;

public interface RolePermService {
	
	//根据角色id查询所有的权限
	List<Integer> getPermIdListByRoleIdList(List<Integer> roleIdList);

}
