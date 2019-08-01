package com.yjh.service;

import java.util.List;

import com.yjh.domain.Sdept;

public interface SdeptService {

	List<Sdept> selectCourseList();

	Integer saveSdept(Sdept sdept);

	int deleteSdeptById(Integer sdeptId);

	Sdept selectSdeptById(Integer sdeptId);

	int updateSdept(Sdept sdept);

	List<Sdept> querySdeptByName(String name);

	int findSdeptByName(String name);

	List<Sdept> getSdeptList();

}
