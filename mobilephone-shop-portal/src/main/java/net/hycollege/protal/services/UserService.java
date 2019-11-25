package net.hycollege.protal.services;

import net.hycollege.message.bean.Message;
import net.hycollege.mybatis.domain.User;

public interface UserService {
	public Message insert(User user);
	public Message update(User user);
	public User select(String username, String password, String uuid);
}
