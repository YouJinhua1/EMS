package com.yjh.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.MajorAdpter;
import com.yjh.adpter.SdeptAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.Major;
import com.yjh.domain.Sdept;
import com.yjh.domain.SysRole;
import com.yjh.domain.SysUser;
import com.yjh.service.MajorService;
import com.yjh.service.SdeptService;
import com.yjh.service.UserService;


@Controller
@RequestMapping("/major")
public class MajorController {
	@Resource
	MajorService majorService;
	
	@Resource
	UserService userService;
	
	@Resource
	SdeptService sdeptService;
	
	@RequiresPermissions("major:query")
	@RequestMapping("/toMajorPage")
	public String queryCourseList(Model model) {
		model.addAttribute("include","/admin/major");
		return "success";
	}
	
	@RequiresPermissions("major:query")
	@RequestMapping("/majorList")
	@ResponseBody
	public ResultData majorList() {
		//查出专业信息
		List<Major> majorList=majorService.selectMajorList();
		List<MajorAdpter> majorAdpterList=new ArrayList<MajorAdpter>();
		//查出每个专业领导人的名字,以及所属的院系名称
		for(Major major:majorList) {
			String leaderName=userService.selectUserNameById(major.getLeaderId());
			Sdept sdept=sdeptService.selectSdeptById(major.getSdeptId());
			MajorAdpter majorAdpter=MajorAdpter.getMajorAdpter(major);
			majorAdpter.setLeaderName(leaderName);
			majorAdpter.setSdeptName(sdept.getName());
			majorAdpterList.add(majorAdpter);
		}
		return 	ResultData.success(majorAdpterList);
	}
	
	@RequiresPermissions("major:save")
	@RequestMapping("/saveMajor")
	@ResponseBody
	public ResultData saveMajor(Major major) {
		int count=majorService.findMajorByNameAndSdeptId(major.getName(),major.getSdeptId());
		if(count>0) {
			return ResultData.fail("添加失败，该专业名称在所属的院系中已经存在！");
		}
		int x=majorService.saveMajor(major);
		if(x>0) {
			return ResultData.success("添加成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	
	@RequiresPermissions("major:query")
	@RequestMapping("/loadLeader")
	@ResponseBody
	public ResultData loadLeader() {
		List<SysUser> leaderList=userService.getMajorLeader();
		if(leaderList!=null&&leaderList.size()>0) {
			return ResultData.success(leaderList);
		}else {
			return ResultData.fail("加载院系负责人数据失败！");
		}
	}
	@RequiresPermissions("major:query")
	@RequestMapping("/loadSdept")
	@ResponseBody
	public ResultData loadSdept(Model model) {
		List<Sdept> sdeptList=sdeptService.getSdeptList();
		if(sdeptList!=null&&sdeptList.size()>0) {
			return ResultData.success(sdeptList);
		}else {
			return ResultData.fail("加载院系负责人数据失败！");
		}
	}
	
	@RequiresPermissions("major:query")
	@RequestMapping("/queryMajorBySdeptId")
	@ResponseBody
	public ResultData queryMajorBySdeptId(@RequestParam("sdeptId")Integer sdeptId) {
		//查出专业信息
		List<Major> majorList=majorService.selectMajorBySdeptId(sdeptId);
		if(!(majorList!=null&&majorList.size()>0)){
			return ResultData.fail("该院系暂时还没有任何专业！");
		}
		List<MajorAdpter> majorAdpterList=new ArrayList<MajorAdpter>();
		//查出每个专业领导人的名字,以及所属的院系名称
		for(Major major:majorList) {
			String leaderName=userService.selectUserNameById(major.getLeaderId());
			Sdept sdept=sdeptService.selectSdeptById(major.getSdeptId());
			MajorAdpter majorAdpter=MajorAdpter.getMajorAdpter(major);
			majorAdpter.setLeaderName(leaderName);
			majorAdpter.setSdeptName(sdept.getName());
			majorAdpterList.add(majorAdpter);
		}
		return 	ResultData.success(majorAdpterList);
	}
	
	@RequiresPermissions("major:query")
	@RequestMapping("/queryMajorByName")
	@ResponseBody
	public ResultData queryMajorByName(@RequestParam("name") String name) {
		List<Major> majorList=majorService.queryMajorByName(name);
		if(!(majorList!=null&&majorList.size()>0)){
			return ResultData.fail("没有查到任何有关："+name+"的信息！");
		}
		List<MajorAdpter> majorAdpterList=new ArrayList<MajorAdpter>();
		//查出每个专业领导人的名字,以及所属的院系名称
		for(Major major:majorList) {
			String leaderName=userService.selectUserNameById(major.getLeaderId());
			Sdept sdept=sdeptService.selectSdeptById(major.getSdeptId());
			MajorAdpter majorAdpter=MajorAdpter.getMajorAdpter(major);
			majorAdpter.setLeaderName(leaderName);
			majorAdpter.setSdeptName(sdept.getName());
			majorAdpterList.add(majorAdpter);
		}
		return 	ResultData.success(majorAdpterList);
	}
	
	@RequiresPermissions("major:delete")
	@RequestMapping("/deleteMajor")
	@ResponseBody
	public ResultData deleteSdept(@RequestParam("id")Integer majorId) {
		int x=majorService.deleteMajorById(majorId);
		if(x>0) {
			return ResultData.success("删除成功！");
		}else {
			return ResultData.fail("该专业尚在使用，无法删除！");
		}
	}
	
	@RequiresPermissions("major:update")
	@RequestMapping("/findMajorById")
	@ResponseBody
	public ResultData findMajorById(@RequestParam("data")Integer data) {
		Major major=majorService.selectMajorById(data);
		if(major!=null) {
			return ResultData.success(major);
		}else {
			return ResultData.fail("该专业已经不存在了！");
		}
	}
	
	@RequiresPermissions("major:update")
	@RequestMapping("/updateMajor")
	@ResponseBody
	public ResultData updateMajor(Major major) {
		int x=majorService.updateMajor(major);
		if(x>0) {
			return ResultData.success("更新成功！");
		}else {
			return ResultData.fail("更新失败！");
		}
	}
	
	
}
