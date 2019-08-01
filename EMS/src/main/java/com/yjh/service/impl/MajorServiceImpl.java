package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.Major;
import com.yjh.domain.Sdept;
import com.yjh.mapper.MajorMapper;
import com.yjh.service.MajorService;
@Service
public class MajorServiceImpl implements MajorService{
	
	@Resource
	MajorMapper majorMapper;

	@Override
	public List<Major> queryMajorByName(String name) {
		return majorMapper.queryMajorByName(name);
	}

	@Override
	public int updateMajor(Major major) {
		return majorMapper.updateByPrimaryKeySelective(major);
	}

	@Override
	public Major selectMajorById(Integer majorId) {
		return majorMapper.selectByPrimaryKey(majorId);
	}

	@Override
	public int deleteMajorById(Integer majorId) {
		return majorMapper.deleteByPrimaryKey(majorId);
	}

	@Override
	public int saveMajor(Major major) {
		return majorMapper.insertSelective(major);
	}

	@Override
	public List<Major> selectMajorList() {
		return majorMapper.selectMajorList();
	}

	@Override
	public int findMajorByNameAndSdeptId(String name, Integer sdeptId) {
		return majorMapper.findMajorByNameAndSdeptId(name,sdeptId);
	}

	@Override
	public List<Major> selectMajorBySdeptId(Integer sdeptId) {
		return majorMapper.selectMajorBySdeptId(sdeptId);
	}
	
	@Override
	public List<Sdept> getMajorList() {
		return majorMapper.getMajorList();
	}

	@Override
	public List<Major> loadMajor(Integer sdeptId) {
		return majorMapper.loadMajorBySdeptId(sdeptId);
	}

	@Override
	public List<Major> loadMajor() {
		return majorMapper.loadMajor();
	}
	
	

	

}
