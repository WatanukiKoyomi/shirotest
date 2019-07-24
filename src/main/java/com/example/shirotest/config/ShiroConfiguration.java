package com.example.shirotest.config;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.shirotest.AuthRealm;
import com.example.shirotest.CredentialMatcher;

@Configuration
public class ShiroConfiguration {

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")DefaultWebSecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/unauthorized");
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/index", "authc");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/loginUser", "anon");
		filterChainDefinitionMap.put("/admin","roles[admin]");
		filterChainDefinitionMap.put("druid/*", "anon");
		filterChainDefinitionMap.put("/**", "user");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return bean;
	}
	
	@Bean("securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}
	
	@Bean("authRealm")
	public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}
	
	@Bean("credentialMatcher")
	public CredentialMatcher credentialMatcher() {
		return new CredentialMatcher();
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")DefaultWebSecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
}
