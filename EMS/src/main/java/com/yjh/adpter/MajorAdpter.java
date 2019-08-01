package com.yjh.adpter;


import org.springframework.beans.BeanUtils;

import com.yjh.domain.Major;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString   
public class MajorAdpter extends Major{
	private String leaderName;
	
	private String sdeptName;
	
	//提供一个静态方法将major转成sdeptAdpter
	public static MajorAdpter getMajorAdpter(Major major) {
		MajorAdpter majorAdpter=new MajorAdpter();
		BeanUtils.copyProperties(major, majorAdpter);
		return majorAdpter;
	}
}
