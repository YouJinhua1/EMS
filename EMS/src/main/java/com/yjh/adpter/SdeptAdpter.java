package com.yjh.adpter;


import org.springframework.beans.BeanUtils;

import com.yjh.domain.Sdept;
import com.yjh.domain.SysPerm;
import com.yjh.domain.SysUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString   
public class SdeptAdpter extends Sdept{
	private String leaderName;
	
	//提供一个静态方法将sdept转成sdeptAdpter
	public static SdeptAdpter getSdeptAdpter(Sdept sdept) {
		SdeptAdpter sdeptAdpter=new SdeptAdpter();
		BeanUtils.copyProperties(sdept, sdeptAdpter);
		return sdeptAdpter;
	}
}
