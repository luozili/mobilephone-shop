package net.hycollege.services;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.Adress;
import net.hycollege.mybatis.domain.User;

public interface UserServices {
	public User select(String username, String password);

	public void insert(User user);

	public void update(User user);

	public Message insertUserAdress(Adress adress);

	public Message updateUserAdress(Adress adress);

	public Message deleteUserAdress(String aid);

	public LayUITableMessage selectUserAdress(String auid);
}
