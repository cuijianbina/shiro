package com.trs.shiro.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name = "shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		System.out.println("过滤");
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/user/persission");
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		filterChainDefinitionMap.put("/js/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;

	}

	@Bean(name = "defaultWebSecurityManager")
	// 创建DefaultWebSecurityManager
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(userRealm);
		return defaultWebSecurityManager;

	}

	// 创建Realm
	@Bean(name = "userRealm")
	public UserRealm getUserRealm() {
		return new UserRealm();
	}

}
