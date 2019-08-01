package com.yjh.service;

import java.util.List;

import com.yjh.domain.Major;
import com.yjh.domain.Sdept;

public interface MajorService {

	List<Major> queryMajorByName(String name);

	int updateMajor(Major major);

	Major selectMajorById(Integer majorId);

	int deleteMajorById(Integer majorId);

	int saveMajor(Major major);

	List<Major> selectMajorList();

	int findMajorByNameAndSdeptId(String name, Integer sdeptId);

	List<Major> selectMajorBySdeptId(Integer sdeptId);

	List<Sdept> getMajorList();

	List<Major> loadMajor(Integer sdeptId);

	List<Major> loadMajor();


}
