package com.yjh.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjh.domain.SysUser;
import com.yjh.service.ToolsService;

@Controller
public class LoginController {
	
	/*@Resource
	PermService permService; 
	
	@Resource
	RolePermService rolePermService; 
	
	@Resource
	RoleUserService roleUserService; */
	
	@Resource
	ToolsService toolsService;

	@RequestMapping("/login")
	public String login(String usercode, String password, HttpServletRequest request,Model model) {
		// 编写shiro的认证操作
		// 1 获取Subject
		Subject subject = SecurityUtils.getSubject();
		// 2 封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(usercode, password);
		// 3 执行登录方法
		try {
			subject.login(token);
			// 登录成功，跳转到成功页面
			SysUser user = (SysUser) subject.getPrincipal();
			String menuData=toolsService.getMenuJson(user.getId(),request);
			if(menuData==null) {
				model.addAttribute("msg", "您的权限不够，请联系管理员！");
				return "index";
			}
				
			String menuData1=menuData.replaceAll("name", "text");
			String menuData2=menuData1.replaceAll("url", "href");
			HttpSession session=request.getSession();
			session.setAttribute("data", menuData2);
			session.setAttribute("user", user);
			model.addAttribute("include", "/common/default");
			return "success";
		} catch (UnknownAccountException e) {
			// 登录失败:用户名不存在
			model.addAttribute("msg1", "账号错误或不存在！");
			// 返回登录首页
			return "index";
		} catch (IncorrectCredentialsException e) {
			// 登录失败:密码错误
			model.addAttribute("msg2", "密码错误！");
			// 返回登录首页
			return "index";
		}
	}

	// 退出系统
	@RequestMapping("/logout")
	public String logout() throws Exception {
		// 获取主体
		Subject subject = SecurityUtils.getSubject();
		// 退出认证
		subject.logout();
		return "index";
	}
}
