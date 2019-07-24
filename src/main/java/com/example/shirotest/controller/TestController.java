package com.example.shirotest.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shirotest.model.User;

@Controller
public class TestController {

	@RequestMapping("/login")
	public String login() {
		System.out.println("login");
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin success";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if(subject != null) {
			subject.logout();
		}
		return "login";
	}
	
	/*
	 * @RequestMapping("/unauthorized") public String unauthorized() { return
	 * "unauthorized"; }
	 */

	@RequestMapping("/loginUser")
	public String loginUser(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session) {
		System.out.println("loginUser  username:"+username+"  password:"+password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println(token.toString());
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.toString());
		try {
			System.out.println("try");
			subject.login(token);
			System.out.println("usergetPrincipal");
			User user = (User) subject.getPrincipal();
			System.out.println(user.toString());
			session.setAttribute("user", user);
			return "redirect:/pages/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println();
			return "redirect:/pages/error.jsp";
		}
	}
}
