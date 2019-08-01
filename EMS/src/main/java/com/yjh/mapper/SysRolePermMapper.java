package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.SysRolePerm;

public interface SysRolePermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePerm record);

    int insertSelective(SysRolePerm record);

    SysRolePerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePerm record);

    int updateByPrimaryKey(SysRolePerm record);
    
    List<Integer> getPermIdListByRoleIdList(List<Integer> roleIdList);

	List<Integer> getPermIdListByRoleId(@Param("id")int roleId);

	void deleteByRoleId(@Param("roleId")int roleId);


	void changeRolePerm(@Param("rolePermList")List<SysRolePerm> rolePermList);
}