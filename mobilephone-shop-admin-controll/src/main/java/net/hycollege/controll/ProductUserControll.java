package net.hycollege.controll;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.message.utils.CheckObjects;
import net.hycollege.services.AdminService;
@RestController
public class ProductUserControll {
	@Autowired
	private AdminService adminService;
	@PostMapping("/admin/login")
	private Message login(String username, String password, HttpSession session) {
		if(CheckObjects.checkObjectNull(username, password) || CheckObjects.checkLeng(username, password)) {
			return new Message(Message.fail);
		}
		Message message = adminService.login(username, password);
		if (message.getStatus() == Message.ok)
			session.setAttribute("login", Message.ok);
		return message;
	}
}
