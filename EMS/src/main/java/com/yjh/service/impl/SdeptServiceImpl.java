package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.Sdept;
import com.yjh.mapper.SdeptMapper;
import com.yjh.service.SdeptService;
@Service
public class SdeptServiceImpl implements SdeptService{
	

	
	@Resource
	SdeptMapper sdeptMapper;

	@Override
	public List<Sdept> selectCourseList() {
		return sdeptMapper.selectCourseList();
	}
	@Override
	public Integer saveSdept(Sdept sdept) {
		return sdeptMapper.insertSelective(sdept);
	}
	@Override
	public int deleteSdeptById(Integer sdeptId) {
		return sdeptMapper.deleteByPrimaryKey(sdeptId);
	}
	@Override
	public Sdept selectSdeptById(Integer sdeptId) {
		return sdeptMapper.selectByPrimaryKey(sdeptId);
	}
	@Override
	public List<Sdept> querySdeptByName(String name) {
		return sdeptMapper.querySdeptByName(name);
	}
	@Override
	public int updateSdept(Sdept sdept) {
		return sdeptMapper.updateByPrimaryKeySelective(sdept);
	}
	
	@Override
	public int findSdeptByName(String name) {
		return sdeptMapper.findSdeptByName(name);
	}
	@Override
	public List<Sdept> getSdeptList() {
		return sdeptMapper.getSdeptList();
	}
	
}
