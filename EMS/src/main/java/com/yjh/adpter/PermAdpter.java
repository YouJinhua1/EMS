package com.yjh.adpter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yjh.domain.SysPerm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString   
public class PermAdpter extends SysPerm{
	
	private List<PermAdpter> nodes=new ArrayList<PermAdpter>();
	
	public static PermAdpter getAdpter(SysPerm perm) {
		PermAdpter permAdpter=new PermAdpter();
		BeanUtils.copyProperties(perm, permAdpter);
		return permAdpter;
	}

}
