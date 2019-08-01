package com.yjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.PermAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.SysPerm;
import com.yjh.service.PermService;
import com.yjh.utils.TreeUtils;

@Controller
@RequestMapping("/perm")
public class PermController {
	@Resource
	PermService permService;
	
	@RequiresPermissions("perm:query")
	@RequestMapping("/permList")
	public String queryPermList(Model model) {
		List<SysPerm> permList=permService.queryPermList();
		//像集合中添加一个不存在的根节点
		permList.add(SysPerm.builder().id(0).name("根节点").parentId(-1).build());
		//得到树形结构的集合
		List<PermAdpter> permAdpterList=TreeUtils.getPermTree(permList);
		model.addAttribute("permTreeList",permAdpterList);
		//然后先序遍历这个集合树
		List<PermAdpter> paList=TreeUtils.getPreorderTraversalList(permAdpterList);
		model.addAttribute("permList",paList);
		model.addAttribute("include","/admin/perm");
		return "success";
		
	}
	
	@RequiresPermissions("perm:save")
	@RequestMapping("/savePerm")
	@ResponseBody
	public ResultData savePerm(SysPerm perm,HttpServletRequest request) {
		if(perm.getCode()==null||perm.getCode().equals("")) {
			perm.setCode("#");
		}
		if(perm.getUrl()==null||perm.getUrl().equals("")) {
			perm.setUrl("#");
		}
		int x=permService.savePerm(perm,request);
		if(x==1) {
			return ResultData.success("添加成功！");
		}
		if(x==-1){
			return ResultData.fail("父层级不存在，添加失败！");
		}
		if(x==0){
			return ResultData.fail("添加失败！");
		}
		if(x==-2){
			return ResultData.fail("同一层级下，存在同名的资源名称！");
		}
		return ResultData.fail("添加失败，未知异常！");
	}
	
	@RequiresPermissions("perm:update")
	@RequestMapping("/updatePerm")
	@ResponseBody
	public ResultData updatePerm(SysPerm perm,HttpServletRequest request) {
		int x=permService.ChangePerm(perm,request);
		if(x==1) {
			return ResultData.success("更新成功！");
		}
		if(x==-1){
			return ResultData.fail("待更新的资源不存在，更新失败！");
		}
		if(x==-2) {
			return ResultData.fail("待更新资源的父层级下有同名称的资源");
		}
		if(x==-3) {
			return ResultData.fail("待更新资源选择的父层级不存在，更新失败！");
		}
		return ResultData.fail("在更新子资源的时候，更新失败！");
	}
	
	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletePerm")
	@ResponseBody
	public ResultData deletePerm(@RequestParam("id")int id,HttpServletRequest request) {
		int x=permService.deletePerm(id,request);
		if(x>0) {
			return ResultData.success("删除成功！");
		}
		if(x==-1){
			return ResultData.fail("当前资源下有子资源,不能删除！");
		}
		if(x==0) {
			return ResultData.fail("删除失败！");
		}
		return ResultData.fail("未知异常，删除失败！");
	}

}
