package net.hycollege.portal.controll;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.mybatis.domain.User;
import net.hycollege.protal.services.UserService;
@RestController
public class UserControll {
	@Autowired(required = true)
	private UserService userService;

	@PostMapping(value = "/user/login")
	public Message login(String username, String password, HttpServletResponse response) {
		String uuid = UUID.randomUUID().toString();
		User user = userService.select(username, password,uuid);
		if (user == null)
			return new Message(Message.fail);
		Cookie cookie = new Cookie("flag", uuid);
		response.addCookie(cookie);
		return new Message(Message.ok);
	}

	@PostMapping("/user/register")
	public Message register(@RequestBody User user) {
		try {
			if (checkUser(user))
				return new Message(Message.fail);
			userService.insert(user);
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
			userService.update(user);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message(Message.ok);
	}
}
