package com.yjh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjh.adpter.AuthUserAdpter;
import com.yjh.adpter.PermAuthAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.SysPerm;
import com.yjh.domain.SysRole;
import com.yjh.domain.SysUser;
import com.yjh.service.RoleService;
import com.yjh.service.ToolsService;
import com.yjh.service.UserService;
import com.yjh.utils.IpUtil;
import com.yjh.utils.StringUtil;
import com.yjh.utils.TreeUtils;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	RoleService roleService;
	
	@Resource
	ToolsService toolsService; 
	
	@Resource
	UserService userService; 
	
	
	@RequiresPermissions("role:query")
	@RequestMapping("/toRolePage")
	public String toSdeptPage(Model model) {
		model.addAttribute("include","/admin/role");
		return "success";
	}
	
	@RequiresPermissions("role:query")
	@RequestMapping("/roleList")
	@ResponseBody
	public ResultData queryRoleList(Model model) {
		return ResultData.success(roleService.selectRoleList());
	}
	
	@RequiresPermissions("role:save")
	@RequestMapping("/saveRole")
	@ResponseBody
	public ResultData saveRole(SysRole role,HttpServletRequest request,HttpSession session) {
		SysUser user=(SysUser) session.getAttribute("user");
		role.setOperator(user.getName());
		role.setOperateIp(IpUtil.getRemoteIp(request));
		role.setOperateTime(new Date());
		int x=roleService.saveRole(role);
		if(x>0) {
			return ResultData.success("添加角色成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	
	//查询要修改的角色信息，并跳转到修改页面
	@RequiresPermissions("role:query")
	@RequestMapping("/findRoleById")
	@ResponseBody
	public ResultData findRoleById(@RequestParam("data") Integer roleId) {
		List<String> sysRoleList=new ArrayList<String>();
		sysRoleList.add("开发人员");
		sysRoleList.add("超级管理员");
		sysRoleList.add("学生");
		sysRoleList.add("教师");
		SysRole role=roleService.selectRoleById(roleId);
		if(role!=null) {
			if(sysRoleList.contains(role.getName())) {
				return ResultData.fail("系统角色不能修改！");
			}else {
				return ResultData.success(role);
			}
		}else {
			return ResultData.fail("该角色已经不存在了！");
		}
	}
	//角色修改
	@RequiresPermissions("role:update")
	@RequestMapping("/updateRole")
	@ResponseBody
	public ResultData updateRole(SysRole role,HttpSession session,HttpServletRequest request) {
		SysUser user=(SysUser) session.getAttribute("user");
		role.setOperator(user.getName());
		role.setOperateIp(IpUtil.getRemoteIp(request));
		role.setOperateTime(new Date());
		int x=roleService.updateRole(role);
		if(x>0) {
			return ResultData.success("更新成功！");
		}else {
			return ResultData.fail("更新失败！");
		}
	}
	
	
	//角色删除
	@RequiresPermissions("role:delete")
	@RequestMapping("/deleteRole")
	@ResponseBody
	public ResultData deleteRole(@RequestParam("id")Integer roleId) {
		List<String> sysRoleList=new ArrayList<String>();
		sysRoleList.add("开发人员");
		sysRoleList.add("超级管理员");
		sysRoleList.add("学生");
		sysRoleList.add("教师");
		SysRole role=roleService.selectRoleById(roleId);
		if(role!=null) {
			if(sysRoleList.contains(role.getName())) {
				return ResultData.fail("系统角色不能删除！");
			}else {
				int x=roleService.deleteRoleById(roleId);
				if(x>0) {
					return ResultData.success("删除成功！");
				}else {
					return ResultData.fail("删除失败！");
				}
			}
		}else {
			return ResultData.fail("要删除的角色已经不存在了！");
		}
	}
	
	@RequiresPermissions("role:query")
	@RequestMapping("/queryRoleByStatus")
	@ResponseBody
	public ResultData queryRoleByStatus(@Param("status") int status,Model model) {
		return ResultData.success(roleService.queryRoleByStatus(status));
	}
	
	@RequiresPermissions("role:query")
	@RequestMapping("/queryRoleByName")
	@ResponseBody
	public ResultData queryRoleByName(@Param("name") String name,Model model) {
		return ResultData.success(roleService.queryRoleByName(name));
	}

	@RequiresPermissions("rolePerm:save")
	@RequestMapping("/saveRolePerm")
	@ResponseBody
	public ResultData saveRolePerm(@RequestParam("roleId") int roleId, @RequestParam(value = "permIds", required = false, defaultValue = "")String permIds,HttpSession session,HttpServletRequest request ) {
		SysUser user=(SysUser) session.getAttribute("user");
		List<Integer> permIdList=StringUtil.splitToListInt(permIds);
		String operator=user.getName();
		String operateIp=IpUtil.getRemoteIp(request);
		toolsService.changeRolePerm(operator,operateIp,roleId,permIdList);
		return ResultData.success("角色与资源授权成功");
	}
	
	
	@RequiresPermissions("role:auth")
	@RequestMapping("/roleAuth")
	@ResponseBody
	public ResultData roleAuth(@RequestParam("id") int roleId,HttpSession session) {
		//查询当前用户的所有权限
		SysUser user=(SysUser) session.getAttribute("user");
		List<SysPerm> userPermList=toolsService.getUserAllPermList(user.getId());
		//查询当前角色的所有权限
		List<SysPerm> rolePermList=toolsService.getRolePermList(roleId);
		//查询系统中的所有权限
		List<SysPerm> allPermList=toolsService.getAllPermList();
		List<PermAuthAdpter> data;
		if(rolePermList!=null&&rolePermList.size()>0) {
			data=TreeUtils.getPermAuthTree(userPermList,rolePermList,allPermList);
		}else {
			data=TreeUtils.getPermAuthTree(userPermList,allPermList);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json=mapper.writeValueAsString(data);
			return ResultData.success(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResultData.success(data);
	}
	
	
	@RequiresPermissions("role:auth")
	@RequestMapping("/queryUser")
	@ResponseBody
	public ResultData queryUser(@RequestParam(value = "name", required = false, defaultValue = "")String name,@RequestParam("roleId") int roleId) {
		List<SysUser> unSelectedUserList=userService.queryUser(name);
		List<SysUser> selectedUserList=userService.queryUserByRoleId(roleId);
		AuthUserAdpter data=new AuthUserAdpter();
		data.setSelectedUserList(selectedUserList);
		data.setUnSelectedUserList(unSelectedUserList);
		if(unSelectedUserList!=null&&unSelectedUserList.size()>0) {
			return ResultData.success(data);
		}else {
			return ResultData.fail("没有查到任何用户！");
		}
	}
	@RequiresPermissions("roleUser:save")
	@RequestMapping("/saveRoleUser")
	@ResponseBody
	public ResultData saveRoleUser(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "")String userIds,HttpSession session,HttpServletRequest request ) {
		SysUser user=(SysUser) session.getAttribute("user");
		List<Integer> userIdList=StringUtil.splitToListInt(userIds);
		String operator=user.getName();
		String operateIp=IpUtil.getRemoteIp(request);
		toolsService.changeRoleUser(operator,operateIp,roleId,userIdList);
		return ResultData.success("角色与用户授权成功！");
	}
	
}
