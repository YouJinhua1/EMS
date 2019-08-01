package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.Sdept;

public interface SdeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sdept record);

    int insertSelective(Sdept record);

    Sdept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sdept record);

    int updateByPrimaryKey(Sdept record);

	List<Sdept> selectCourseList();

	List<Sdept> querySdeptByName(@Param("name")String name);

	int findSdeptByName(@Param("name") String name);

	List<Sdept> getSdeptList();
}