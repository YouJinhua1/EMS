package com.yjh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yjh.domain.SysPerm;

public interface ToolsService {
	String getMenuJson(Integer userId,HttpServletRequest request);
	
	List<String> getUserPermList(Integer userId);

	List<SysPerm> getUserAllPermList(Integer id);

	List<SysPerm> getRolePermList(int roleId);

	List<SysPerm> getAllPermList();

	void changeRolePerm(String operator, String operateIp, int roleId, List<Integer> permIdList);

	void changeRoleUser(String operator, String operateIp, int roleId, List<Integer> userIdList);
}
