package com.yjh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.yjh.adpter.PermAdpter;
import com.yjh.domain.SysPerm;
import com.yjh.domain.SysRolePerm;
import com.yjh.domain.SysRoleUser;
import com.yjh.mapper.SysPermMapper;
import com.yjh.mapper.SysRolePermMapper;
import com.yjh.mapper.SysRoleUserMapper;
import com.yjh.service.ToolsService;
import com.yjh.utils.TreeUtils;

@Service
public class ToolsServiceImpl implements ToolsService {

	@Resource
	SysRolePermMapper rolePermMapper;

	@Resource
	SysPermMapper permMapper;

	@Resource
	SysRoleUserMapper roleUserMapper;

	public String getMenuJson(Integer userId,HttpServletRequest request) {
		// 根据用户id查询所有的角色id
		List<Integer> roleIdList = roleUserMapper.getRoleIdByUserId(userId);
		//如果用户没有角色，直接返回空
		if(!(roleIdList!=null&&roleIdList.size()>0)) 
			return null;
		// 根据角色id查询所有的权限id
		List<Integer> permIdList = rolePermMapper.getPermIdListByRoleIdList(roleIdList);
		//如果没有查到权限id（即：角色还没有权限，或者角色被冻结），直接返回空
		if(!(permIdList!=null&&permIdList.size()>0))	
			return null;
		// 根据权限id查询该用户所拥有的操作菜单目录
		List<SysPerm> permList = permMapper.getPermMenuByPermIdList(permIdList);
		//解决前台路径跳转问题
		for(SysPerm perm:permList) {
			if(perm.getUrl()!="#") {
				perm.setUrl(request.getContextPath()+perm.getUrl());
			}
		}
		List<PermAdpter> treeList = TreeUtils.getTree(permList);
		ObjectMapper mapper = new ObjectMapper();
		try {
				String json=mapper.writeValueAsString(treeList);
				return json;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	//登录后授权使用的方法
	public List<String> getUserPermList(Integer userId) {
		// 根据用户id查询所有的角色id
		List<Integer> roleIdList = roleUserMapper.getRoleIdByUserId(userId);
		if(!(roleIdList!=null&&roleIdList.size()>0)) 
			return null;
		// 根据角色id查询所有的权限id
		List<Integer> permIdList = rolePermMapper.getPermIdListByRoleIdList(roleIdList);
		//如果没有查到权限id（即：角色还没有权限，或者角色被冻结），直接返回空
		if(!(permIdList!=null&&permIdList.size()>0))	
			return null;
		// 根据权限id查询该用户所有的权限
		List<SysPerm> permList = permMapper.getPermByPermIdList(permIdList);
		//封装perm集合
		List<String> list=new ArrayList<String>();
		for(SysPerm perm:permList) {
			list.add(perm.getCode());
		}
		return list;
	}
	
	public List<SysPerm> getUserAllPermList(Integer userId) {
		// 根据用户id查询所有的角色id
		List<Integer> roleIdList = roleUserMapper.getRoleIdByUserId(userId);
		//判断用户是否有角色
		if(roleIdList!=null&&roleIdList.size()>0) {
			// 根据角色id查询所有的权限id
			List<Integer> permIdList = rolePermMapper.getPermIdListByRoleIdList(roleIdList);
			//判断用户所拥有的角色是否有权限
			if(permIdList!=null&&permIdList.size()>0) {
				// 根据权限id查询该用户所有的权限
				List<SysPerm> permList = permMapper.getPermByPermIdList(permIdList);
				return permList;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	@Override
	public List<SysPerm> getRolePermList(int roleId) {
		List<Integer> permIdList=rolePermMapper.getPermIdListByRoleId(roleId);
		//判断角色是否有权限
		if(permIdList!=null&&permIdList.size()>0) {
			List<SysPerm> permList = permMapper.getPermByPermIdList(permIdList);
			return permList;
		}else {
			return null;
		}
	}

	@Override
	public List<SysPerm> getAllPermList() {
		return permMapper.getAllPermList();
	}

	@Override
	@Transactional
	public void changeRolePerm(String operator, String operateIp, int roleId,
			List<Integer> permIdList) {
		rolePermMapper.deleteByRoleId(roleId);
		if (CollectionUtils.isEmpty(permIdList)) {
	            return;
	    }
		List<SysRolePerm> rolePermList = Lists.newArrayList();
		 for(Integer permId : permIdList) {
	            SysRolePerm rolePerm = SysRolePerm.builder().roleId(roleId).permId(permId).operator(operator)
	                    .operateIp(operateIp).operateTime(new Date()).build();
	            rolePermList.add(rolePerm);
	        }
		    rolePermMapper.changeRolePerm(rolePermList);
	}

	@Override
	@Transactional
	public void changeRoleUser(String operator, String operateIp, int roleId, List<Integer> userIdList) {
 		roleUserMapper.deleteByRoleId(roleId);
 		if(CollectionUtils.isEmpty(userIdList)) {
 			return ;
 		}
 		List<SysRoleUser> roleUserList=Lists.newArrayList();
 		for(Integer userId:userIdList) {
 			SysRoleUser roleUser=SysRoleUser.builder().roleId(roleId).userId(userId).operator(operator)
            .operateIp(operateIp).operateTime(new Date()).build();
 			roleUserList.add(roleUser);
 		}
 		roleUserMapper.changeRoleUser(roleUserList);
	}
	



	

}
