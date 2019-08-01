package com.yjh.utils;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @title: LevelUtil
 * @description: 计算层级的工具类
 * @author: YouJinhua 
 * @date: 2019年5月4日 上午10:41:49
 */
public class LevelUtils {

	// 定义各个层级之间的分割符
	public final static String SEPARATOR = ".";

	// 定义root层级的id为0
	public final static String ROOT = "0";

	public static String calculateLevel(String parentLevel, int parentId) {
		// 如果父层级是空的话，认为它就是首层
		if (StringUtils.isBlank(parentLevel)) {
			return ROOT;
		} else {
			// 0
			// 0.1
			// 0.1.2
			// 0.1.3
			// 0.4
			return StringUtils.join(parentLevel, SEPARATOR, parentId);
		}
	}
}
