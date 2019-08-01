package com.yjh.service;

import java.util.List;

public interface RoleUserService {

	//根据用户id查询所有的角色id
	List<Integer> getRoleIdByUserId(Integer userId);
}
