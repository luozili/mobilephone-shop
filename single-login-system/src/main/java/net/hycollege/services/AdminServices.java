package net.hycollege.services;

import net.hycollege.message.bean.Message;

public interface AdminServices {
	public Message select(String username, String password);
}
