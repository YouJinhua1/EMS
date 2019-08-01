package com.yjh.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData {

	// 定义请求的结果
	private boolean result;
	// 定义请求出现异常时返回的消息
	private String msg;
	// 定义请求返回的数据
	private Object data;

	// 定义一个只有请求结果的构造函数
	public ResultData(boolean result) {
		this.result = result;
	}

	// 定义正常请求成功时，并且有数据和消息返回到前台的方法
	public static ResultData success(String msg, Object data) {
		ResultData resultData = new ResultData(true);
		resultData.msg = msg;
		resultData.data = data;
		return resultData;
	}

	// 定义正常请求成功时，没有数据只有消息返回到前台的方法
	public static ResultData success(String msg) {
		ResultData resultData = new ResultData(true);
		resultData.msg = msg;
		return resultData;
	}

	// 定义正常请求成功时，没有数据只有消息返回到前台的方法
	public static ResultData success(Object data) {
		ResultData resultData = new ResultData(true);
		resultData.data = data;
		return resultData;
	}

	// 定义正常请求成功时，没有数据和消息返回到前台的方法
	public static ResultData success() {
		return new ResultData(true);
	}

	// 定义请求失败时，将消息返回到前台的方法
	public static ResultData fail(String msg) {
		ResultData resultData = new ResultData(false);
		resultData.msg = msg;
		return resultData;
	}
}
