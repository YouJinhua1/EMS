package com.yjh.adpter;

import java.util.List;

import com.yjh.domain.SysUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString 
public class AuthUserAdpter {
	private List<SysUser> unSelectedUserList;
	private List<SysUser> selectedUserList;
}
