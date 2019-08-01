package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectRoleList();
    
    int getRoleCountByStatus(@Param("status") Integer status);

	int getRoleCount();

	List<SysRole> queryRoleByStatus(@Param("status") int status);

	List<SysRole> queryRoleByName(@Param("name")String name);

	Integer getRoleIdByName(@Param("name")String name);
}