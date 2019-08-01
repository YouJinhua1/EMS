package com.yjh.config;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.yjh.domain.SysUser;
import com.yjh.service.ToolsService;
import com.yjh.service.UserService;

public class ShiroRealm extends AuthorizingRealm{

	@Resource
	UserService userService; 
	
	@Resource
	ToolsService toolsService; 
	
	
	//执行授权逻辑的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		//获取当前登录的用户,当前用户就是在通过认证时传入的第一个参数
		Subject subject=SecurityUtils.getSubject();
		SysUser user=(SysUser) subject.getPrincipal();
		//查询数据库，取出该用户的所有权限
		List<String> permList=toolsService.getUserPermList(user.getId());
		if(permList!=null&&permList.size()>0) {
			//将权限添加至info中
			info.addStringPermissions(permList);
		}
		return info;
	}

	//执行认证逻辑的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//编写shiro的认证判断逻辑
		// 1   从token中取出账号
		UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
		// 2   根据账号查询用户
		SysUser user=userService.findUserByNumber(token.getUsername());
		//判断用户是否存在
		if(user==null) {
			//用户不存在,返回null，而shiro底层就会抛出UnknownAccountException异常
			return null;
		}
		//判断密码
		/**
		 * 第一个参数：从数据中查询到的用户，在后面的授权的时候要用的。
		 * 第二个参数：就是从数据库中查询到的密码。
		 * 第三个参数：就是加密所使用的盐（salt）
		 * 第四个参数：就是当前自定义的realm的名称，可以直接传入一个空字符串
		 */
		return new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),"");
		
	}

}
