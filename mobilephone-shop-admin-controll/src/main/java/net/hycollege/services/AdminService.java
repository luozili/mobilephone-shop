package net.hycollege.services;

import net.hycollege.message.bean.Message;

public interface AdminService {
	public Message login(String username, String password);
}
