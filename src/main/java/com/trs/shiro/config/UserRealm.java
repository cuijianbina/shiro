package com.trs.shiro.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
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

import lombok.extern.slf4j.Slf4j;

/**
 * Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。
 * 也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息
 * 
 * @author Administrator
 *
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	// 执行授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("授权");
		// 获取当前登录用户
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		String username = user.getName();
		List<String> roles = userService.findRolesByUsername(username);
		Set<String> rolesSet = new HashSet<>(roles);
		simpleAuthorizationInfo.setRoles(rolesSet);
		List<String> permissions = userService.findPermissionsByUsername(username);
		Set<String> permissionsSet = new HashSet<>(permissions);
		simpleAuthorizationInfo.setStringPermissions(permissionsSet);
		return simpleAuthorizationInfo;
	}

	// 执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		System.out.println("认证");
		// shiro判断逻辑
		// UsernamePasswordToken user = (UsernamePasswordToken)
		// authenticationToken;
		// User realUser = new User();
		// realUser.setName(user.getUsername());
		// log.info("username:"+username);

		String username = (String) authenticationToken.getPrincipal(); // 获取用户名
		String password = String.copyValueOf((char[]) authenticationToken.getCredentials());// 获取密码
		User u = userService.findByUsername(username);
		if (u == null) {
			throw new UnknownAccountException("该账号不存在");
		}
		User newUser = userService.findUser(username, password);
		if (newUser == null) {
			throw new IncorrectCredentialsException("用户名或密码错误");
		} else {
			if (newUser.getStatus() == 1) {
				throw new LockedAccountException("该账号已被锁定");
			}
		}
		return new SimpleAuthenticationInfo(newUser, newUser.getPassword(), "");
	}

}
