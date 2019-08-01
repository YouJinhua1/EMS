package com.yjh.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver{

    @ExceptionHandler(Exception.class)
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		ModelAndView mv = new ModelAndView("/common/exception"); 
		
		mv.addObject("url",request.getRequestURI());
		mv.addObject("detail", e.toString());
		//判断不同异常类型，设置不同的提示消息
		if (e instanceof UnauthorizedException) {
			mv.addObject("msg","您的权限不足，请联系管理员！");
		}else 
		if (e instanceof UnauthenticatedException) {
			mv.addObject("msg", "您尚未登录，请登录后重试！");
		}else {
			mv.addObject("msg", "系统错误，管理员正在抢修！");
		}
		return mv;

	}

}