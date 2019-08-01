package com.yjh.adpter;

import org.springframework.beans.BeanUtils;

import com.yjh.domain.SysUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UserAdpter extends SysUser{
	private String sdeptName;
	private String majorName;
	private String teamName;
	//提供一个静态方法将major转成sdeptAdpter
	public static UserAdpter getUserAdpter(SysUser user) {
		UserAdpter userAdpter=new UserAdpter();
		BeanUtils.copyProperties(user, userAdpter);
		return userAdpter;
	}
	
	
}
