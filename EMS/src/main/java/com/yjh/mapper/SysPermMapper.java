package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.SysPerm;

public interface SysPermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPerm record);

    int insertSelective(SysPerm record);

    SysPerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPerm record);

    int updateByPrimaryKey(SysPerm record);

	List<SysPerm> getPermByPermIdList(List<Integer> permIdList);

	List<SysPerm> getPermMenuByPermIdList(List<Integer> permIdList);

	List<SysPerm> getAllPermList();

	int selectByParentId(@Param("id")int id);

	int countByNameAndParentId(@Param("parentId")Integer parentId, @Param("permName")String permName, @Param("id")Integer id);

	void batchUpdateLevel(@Param("permList")List<SysPerm> permList);

	List<SysPerm> getChildPermListByLevel(@Param("level")String level);

    
}