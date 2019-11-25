package net.hycollege.services;

import net.hycollege.mybatis.domain.User;

public interface UserServices {
	public User select(String username, String password);
	public void insert(User user);
	public void update(User user);
}
