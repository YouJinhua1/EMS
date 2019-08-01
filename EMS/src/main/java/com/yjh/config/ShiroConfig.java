package com.yjh.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.yjh.exception.GlobalExceptionHandler;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	
	
	 	@Bean
	    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
	        return new LifecycleBeanPostProcessor();
	    }
	    @Bean
	    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
	        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	        autoProxyCreator.setProxyTargetClass(true);
	        return autoProxyCreator;
	    }
	    
		@Bean
	    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
	            DefaultWebSecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
	        advisor.setSecurityManager(securityManager);
	        return advisor;
	    }
	


	    //第一步创建shiroFilterFactoryBean
		@Bean
		public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
			//创建shiroFilterFactoryBean
			ShiroFilterFactoryBean shiroFilterFactory=new ShiroFilterFactoryBean();
			//关联安全管理器DefaultWebSecurityManager
			shiroFilterFactory.setSecurityManager(securityManager);
			
			/**添加shiro内置的过滤器
			 *   anon:无需认证（登录）就可以访问
			 *   authc：必须认证才可以访问
			 *   perms：该资源必须得到授权才可以访问
			 *   role：该资源必须得到相应的角色权限才可以访问
			 *   ......
			 *   user:如果使用了RememberMe的功能，可以直接访问
			 *   
			 */
			Map<String,String> filterMap=new LinkedHashMap<String,String>();
			//左边放的是要拦截的资源路径，右边放的是相应的权限
			filterMap.put("/toSuccess", "authc");
			//user目录下的所有文件都需要认证才能访问
			filterMap.put("/user/*", "authc");
			//登录访问路径设置为不需要认证
			filterMap.put("/login", "anon");
			//设置添加操作必须有权限才可以访问
			//filterMap.put("/add", "perms[user:add]");
			//filterMap.put("/update", "perms[user:update]");
			
			//设置认证失败，跳转的路径
			shiroFilterFactory.setLoginUrl("/toIndex");
			//设置没有权限时跳转到提示页面
			shiroFilterFactory.setUnauthorizedUrl("/success");
			shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
			return shiroFilterFactory;
		}
		//第二步创建DefaultWebSecurityManger
		@Bean
		public DefaultWebSecurityManager getDefaultWebSecurityManager() {
			DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
			//关联Realm
			securityManager.setRealm(getRealm());
			//securityManager.setSessionManager(getSessionManager());
			return securityManager;
		}
		//第三步创建Realm
		@Bean
		public ShiroRealm getRealm() {
			ShiroRealm shiroRealm=new ShiroRealm();
			//关联认证匹配器
			shiroRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
			return shiroRealm;
		}
		
		/**
		     * 密码校验规则HashedCredentialsMatcher
		     * 这个类是为了对密码进行编码的 ,
		     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
		     * 这个类也负责对form里输入的密码进行编码
		     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
	     */
		@Bean
		public HashedCredentialsMatcher getHashedCredentialsMatcher() {
			HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
			// 指定加密方式为MD5
			credentialsMatcher.setHashAlgorithmName("MD5");
			// 加密次数
			credentialsMatcher.setHashIterations(1);
			return credentialsMatcher;
		}
		
		@Bean
	    public SessionManager getSessionManager() {
	        ShiroSessionManager sessionManager = new ShiroSessionManager();
	        return sessionManager;
	    }
		/**
		 * 配置shiroDialect，用于thymeleaf和shiro标签的配合使用
		 */
		@Bean
		public ShiroDialect getShiroDialect() {
			return new ShiroDialect();
		}
		
	
}
