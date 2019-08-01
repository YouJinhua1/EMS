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
public class PermAuthAdpter extends SysPerm{
	
	 // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasPerm = false;
    
    public static PermAuthAdpter getPermAuthAdpter(SysPerm perm) {
    	PermAuthAdpter permAuthAdpter=new PermAuthAdpter();
		BeanUtils.copyProperties(perm, permAuthAdpter);
		return permAuthAdpter;
	}

}
