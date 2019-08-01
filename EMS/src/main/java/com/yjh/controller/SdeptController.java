package com.yjh.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.SdeptAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.Sdept;
import com.yjh.domain.SysUser;
import com.yjh.service.SdeptService;
import com.yjh.service.UserService;


@Controller
@RequestMapping("/sdept")
public class SdeptController {
	@Resource
	SdeptService sdeptService;
	
	@Resource
	UserService userService;
	
	
	@RequiresPermissions("sdept:query")
	@RequestMapping("/toSdeptPage")
	public String toSdeptPage(Model model) {
		model.addAttribute("include","/admin/sdept");
		return "success";
	}
	
	@RequiresPermissions("sdept:query")
	@RequestMapping("/sdeptList")
	@ResponseBody
	public ResultData sdeptList(Model model) {
		//查出院系信息
		List<Sdept> sdeptList=sdeptService.selectCourseList();
		List<SdeptAdpter> sdeptAdpterList=new ArrayList<SdeptAdpter>();
		//查出每个院系领导人的名字
		for(Sdept sdept:sdeptList) {
			String leaderName=userService.selectUserNameById(sdept.getLeaderId());
			SdeptAdpter sdeptAdpter=SdeptAdpter.getSdeptAdpter(sdept);
			sdeptAdpter.setLeaderName(leaderName);
			sdeptAdpterList.add(sdeptAdpter);
		}
		return 	ResultData.success(sdeptAdpterList);
	}
	
	@RequiresPermissions("sdept:query")
	@RequestMapping("/querySdeptByName")
	@ResponseBody
	public ResultData queryRoleByName(@Param("name") String name,Model model) {
		List<Sdept> sdeptList=sdeptService.querySdeptByName(name);
		List<SdeptAdpter> sdeptAdpterList=new ArrayList<SdeptAdpter>();
		//查出每个院系领导人的名字
		for(Sdept sdept:sdeptList) {
			String leaderName=userService.selectUserNameById(sdept.getLeaderId());
			SdeptAdpter sdeptAdpter=SdeptAdpter.getSdeptAdpter(sdept);
			sdeptAdpter.setLeaderName(leaderName);
			sdeptAdpterList.add(sdeptAdpter);
		}
		return 	ResultData.success(sdeptAdpterList);
	}
	
	@RequiresPermissions("sdept:save")
	@RequestMapping("/getLeader")
	@ResponseBody
	public ResultData getLeader(Model model) {
		List<SysUser> leaderList=userService.getSdeptLeader();
		if(leaderList!=null&&leaderList.size()>0) {
			return ResultData.success(leaderList);
		}else {
			return ResultData.fail("加载院系负责人数据失败！");
		}
	}
	
	@RequiresPermissions("sdept:save")
	@RequestMapping("/saveSdept")
	@ResponseBody
	public ResultData saveSdept(Sdept sdept) {
		int count=sdeptService.findSdeptByName(sdept.getName());
		if(count>0) {
			return ResultData.fail("添加失败，该院系名称已经存在！");
		}
		int x=sdeptService.saveSdept(sdept);
		if(x>0) {
			return ResultData.success("添加成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	
	@RequiresPermissions("sdept:delete")
	@RequestMapping("/deleteSdept")
	@ResponseBody
	public ResultData deleteSdept(@RequestParam("id")Integer sdeptId) {
		int x=sdeptService.deleteSdeptById(sdeptId);
		if(x>0) {
			return ResultData.success("删除成功！");
		}else {
			return ResultData.fail("该院系尚在使用，无法删除！");
		}
	}
	
	@RequiresPermissions("sdept:query")
	@RequestMapping("/findSdeptById")
	@ResponseBody
	public ResultData findSdeptById(@RequestParam("data") Integer sdeptId) {
		Sdept sdept=sdeptService.selectSdeptById(sdeptId);
		if(sdept!=null) {
			return ResultData.success(sdept);
		}else {
			return ResultData.fail("该学院已经不存在了！");
		}
	}
	
	@RequiresPermissions("sdept:update")
	@RequestMapping("/updateSdept")
	@ResponseBody
	public ResultData updateRole(Sdept sdept) {
		int x=sdeptService.updateSdept(sdept);
		if(x>0) {
			return ResultData.success("更新成功！");
		}else {
			return ResultData.fail("更新失败！");
		}
	}

}
