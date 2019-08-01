package com.yjh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yjh.common.ResultData;
import com.yjh.service.UserService;

@RestController
public class UserController {
	
	@Resource
	UserService userService;

	@RequestMapping("/selectUser")
	public ResultData toIndex() {
		return ResultData.success(userService.getUser());
	}
}
