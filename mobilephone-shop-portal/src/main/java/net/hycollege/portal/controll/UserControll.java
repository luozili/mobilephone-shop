package net.hycollege.portal.controll;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.Adress;
import net.hycollege.mybatis.domain.User;
import net.hycollege.portal.util.CookieUtils;
import net.hycollege.protal.services.UserService;

@RestController
public class UserControll {
	@Autowired(required = true)
	private UserService userService;

	@GetMapping("/login")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/login.html");
	}
	@PostMapping(value = "/user/login")
	public Message login(String username, String password, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User user = userService.select(username, password);
		if (user == null)
			return new Message(Message.fail);
		Cookie cookie = new Cookie("flag", user.getUid());
		cookie.setMaxAge(2000000);
		cookie.setPath("/");
		response.addCookie(cookie);
		return new Message(Message.ok);
	}
	@GetMapping(value = "/user/get")
	public User getUser(HttpServletRequest request) throws Exception {
		return userService.getUser(request);
	}

	@PostMapping("/user/register")
	public Message register(User user) {
		try {
			if (checkUser(user))
				return new Message(Message.fail);
			userService.insert(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(Message.fail);
		}
		return new Message(Message.ok);
	}

	private boolean checkUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		if (username == null || username.length() == 0 || username.length() > 20 || password == null
				|| password.length() == 0)
			return true;
		return false;
	}

	@PostMapping("/user/update")
	public Message update(User user,HttpServletRequest request) {
		try {
			userService.update(user, request);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message(Message.ok);
	}

	@PostMapping("/user/adress/insert")
	public Message insertUserAdress(@RequestBody Adress adress) {
		try {
			return userService.insertUserAdress(adress);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
	}

	@PostMapping("/user/adress/delete")
	public Message deleteUserAdress(String aid) {
		try {
			return userService.deleteUserAdress(aid);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
	}

	@PostMapping("/user/adress/update")
	public Message updateUserAdress(@RequestBody Adress adress) {
		try {
			return userService.updateUserAdress(adress);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
	}

	@GetMapping("/user/adress/select")
	public LayUITableMessage selectUserAdress(String auid) {
		try {
			return userService.selectUserAdress(auid);
		} catch (Exception e) {
			return null;
		}
	}
}
