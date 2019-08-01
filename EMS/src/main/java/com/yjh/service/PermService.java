package com.yjh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yjh.domain.SysPerm;

public interface PermService {

	List<SysPerm> getPermByPermIdList(List<Integer> permIdList);

	List<SysPerm> queryPermList();

	int ChangePerm(SysPerm perm, HttpServletRequest request);

	int deletePerm(int id, HttpServletRequest request);

	int savePerm(SysPerm perm, HttpServletRequest request);
}
