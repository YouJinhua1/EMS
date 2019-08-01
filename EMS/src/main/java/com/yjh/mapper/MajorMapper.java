package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.Major;
import com.yjh.domain.Sdept;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKeyWithBLOBs(Major record);

    int updateByPrimaryKey(Major record);

	List<Major> queryMajorByName(@Param("name")String name);

	List<Major> selectMajorList();

	int findMajorByNameAndSdeptId(@Param("name")String name,@Param("sdeptId") Integer sdeptId);

	List<Major> selectMajorBySdeptId(@Param("sdeptId")Integer sdeptId);

	List<Sdept> getMajorList();

	List<Major> loadMajorBySdeptId(@Param("sdeptId")Integer sdeptId);

	List<Major> loadMajor();
}