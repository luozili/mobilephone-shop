package net.hycollege.admin.console.services;

import net.hycollege.message.bean.Message;

public interface AdminService {
	public Message login(String username, String password);
}
