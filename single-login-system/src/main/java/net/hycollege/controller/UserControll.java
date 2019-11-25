package net.hycollege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.mybatis.domain.User;
import net.hycollege.services.AdminServices;
import net.hycollege.services.UserServices;
@RestController
public class UserControll {
	@Autowired(required = true)
	private UserServices userServices;
	@Autowired(required = true)
	private AdminServices adminServices;

	@PostMapping(value = "/user/login")
	public User login(String username, String password) {
		return userServices.select(username, password);
	}
	@PostMapping(value = "/admin/login")
	public Message adminLogin(String username, String password) {
		return adminServices.select(username, password);
	}
	@PostMapping("/user/register")
	public Message register(@RequestBody User user) {
		try {
			if (checkUser(user))
				return new Message(Message.fail);
			userServices.insert(user);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message(Message.ok);
	}

	private boolean checkUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		if (username == null || username.length() == 0 || username.length() > 20 || password == null
				|| password.length() == 0)
			return false;
		return true;
	}

	@PostMapping("/user/update")
	public Message update(@RequestBody User user) {
		try {
			userServices.update(user);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message(Message.ok);
	}
}
