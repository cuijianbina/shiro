package com.trs.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trs.shiro.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@RequiresPermissions("user:man")
	@RequestMapping("/man")
	public String man() {
		return "man";
	}

	@RequiresPermissions("user:female")
	@RequestMapping("/female")
	public String female() {
		return "female";
	}

	@RequestMapping("/index")
	public String index(User user, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken userToken = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(userToken);// 还有一个logout，用于退出
		} catch (UnknownAccountException e) {
			// 用户名不存在
			model.addAttribute("msg", e.getMessage());
			return "login";
		} catch (IncorrectCredentialsException e) {
			// 密码错误
			model.addAttribute("msg", e.getMessage());
			return "login";
		} catch (LockedAccountException e) {
			model.addAttribute("msg", e.getMessage());
			return "login";
		}
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/permission")
	public String permission() {
		return "permission";
	}

}
