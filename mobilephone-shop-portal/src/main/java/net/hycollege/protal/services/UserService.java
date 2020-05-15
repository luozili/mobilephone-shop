package net.hycollege.protal.services;

import javax.servlet.http.HttpServletRequest;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.Adress;
import net.hycollege.mybatis.domain.User;

public interface UserService {
	public Message insert(User user);

	public Message update(User user, HttpServletRequest request);

	public User select(String username, String password);

	public Message insertUserAdress(Adress adress);

	public Message updateUserAdress(Adress adress);

	public Message deleteUserAdress(String aid);

	public LayUITableMessage selectUserAdress(String auid);

	public User getUser(HttpServletRequest request);
}
