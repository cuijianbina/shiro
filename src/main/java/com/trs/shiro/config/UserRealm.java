package com.trs.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.trs.shiro.domain.User;
import com.trs.shiro.service.UserService;

public class UserRealm  extends AuthorizingRealm {

	@Autowired
	private UserService UserService;
	//执行授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("授权");
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		//给资源授权
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermission(user.getPerms());
		return simpleAuthorizationInfo;
	}
    //执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0){
		// TODO Auto-generated method stub
		System.out.println("认证");
		//shiro判断逻辑
		UsernamePasswordToken user = (UsernamePasswordToken) arg0;
	    User realUser = new User();
	    realUser.setName(user.getUsername());
	    User u = UserService.findByUsername(user.getUsername());
	    if(u==null){
	    	throw new UnknownAccountException();
	    }
	    realUser.setPassword(String.copyValueOf(user.getPassword()));//返回指定数组的字符序列
		User newUser = UserService.findUser(realUser);
		//System.out.println(user.getUsername());
		if(newUser == null){
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo(newUser,newUser.getPassword(),"");
	}


}
