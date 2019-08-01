package com.yjh.exception;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler2 {

	// 所有的异常都是Exception子类
	@ExceptionHandler(Exception.class)
	// 出现异常之后会跳转到此方法
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) {
		ModelAndView mv=new ModelAndView("/common/default");
		mv.addObject("url", request.getRequestURL());
		mv.addObject("detail",e.toString());
		e.printStackTrace();
		if (e instanceof UnauthorizedException) {
			mv.addObject("msg", "亲，您的权限不足！");
			return mv;
		} else if (e instanceof UnauthenticatedException) {
			mv.addObject("msg", "您尚未登录，请登录后重试！");
			return mv;
		} else {
			mv.addObject("msg", "系统错误请联系管理员！");
			return mv;
		}
	}

	// 所有的异常都是Exception子类
	// @ExceptionHandler(Exception.class)
	// 出现异常之后会跳转到此方法
	public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

		if (isAjax(request)) {
			return null;
		} else {
			// 设置跳转路径
			ModelAndView mv = new ModelAndView("/common/error");
			// 将异常对象传递过去
			mv.addObject("exception", e);
			// 获得请求的路径
			mv.addObject("url", request.getRequestURL());
			return mv;
		}

	}

	// 判断是否是ajax请求，这是固定写法
	public static boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

}















/*package com.yjh.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		ModelAndView mv = new ModelAndView("/common/excption"); 
		
		mv.addObject("url",request.getRequestURI());
		mv.addObject("detail", e.toString());
		//判断不同异常类型，设置不同的提示消息
		if (e instanceof UnauthorizedException) {
			Model m=(Model) mv.getModel();
			m.addAttribute("include","/admin/noperms");
			mv.addObject("msg","您的权限不足，请联系管理员！");
			System.out.println(mv.getModel().get("include"));
			mv.setViewName("success");
			return mv;
		}else 
		if (e instanceof UnauthenticatedException) {
			mv.addObject("msg", "您尚未登录，请登录后重试！");
		}else {
			mv.addObject("msg", "您尚未登录，请登录后重试！");
		}
		return mv;

	}

}*/
