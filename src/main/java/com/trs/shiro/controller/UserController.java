package com.trs.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trs.shiro.domain.User;

@Controller
public class UserController {

	@RequestMapping("/hello")
	public String hello() {
		return "hellllll";
	}

	@RequestMapping("/index")
	public String index(User user, Model model) {
		// String name = request.getParameter("name");
		// String password = request.getParameter("password");
		// if(name.equals("")||name.length()==0||name==null){
		// request.setAttribute("msg", "用户名不能为空");
		// return "login";
		// }
		// return "index";
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken userToken = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(userToken);//还有一个logout，用于退出
		} catch (UnknownAccountException e) {
			// 用户名不存在
			model.addAttribute("msg", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			// 密码错误
			model.addAttribute("msg", "密码错误");
			return "login";
		}
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
