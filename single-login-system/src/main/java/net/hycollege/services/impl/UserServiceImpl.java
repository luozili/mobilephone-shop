package net.hycollege.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import net.hycollege.mapper.AdressMapper;
import net.hycollege.mapper.UserMapper;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.Adress;
import net.hycollege.mybatis.domain.AdressExample;
import net.hycollege.mybatis.domain.User;
import net.hycollege.mybatis.domain.UserExample;
import net.hycollege.mybatis.domain.UserExample.Criteria;
import net.hycollege.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired(required = true)
	private UserMapper userMapper;
	@Autowired(required = true)
	private AdressMapper adressMapper;

	@Override
	public User select(String username, String password) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username).andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<User> users = userMapper.selectByExample(userExample);
		if (users == null || users.size() != 1)
			return null;
		return users.get(0);
	}

	@Override
	public void insert(User user) {
		user.setUid(UUID.randomUUID().toString());
		String password = user.getPassword();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		user.setPassword(password);
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public Message insertUserAdress(Adress adress) {
		int insert = adressMapper.insertSelective(adress);
		if (insert > 0) {
			return new Message();
		}
		return new Message(Message.fail);
	}

	@Override
	public Message updateUserAdress(Adress adress) {
		int update = adressMapper.updateByPrimaryKeySelective(adress);
		if (update > 0) {
			return new Message();
		}
		return new Message(Message.fail);
	}

	@Override
	public Message deleteUserAdress(String aid) {
		int delete = adressMapper.deleteByPrimaryKey(aid);
		if (delete > 0) {
			return new Message();
		}
		return new Message(Message.fail);
	}

	@Override
	public LayUITableMessage selectUserAdress(String auid) {
		AdressExample example = new AdressExample();
		example.createCriteria().andAuidEqualTo(auid);
		List<Adress> list = adressMapper.selectByExample(example);
		LayUITableMessage layUITableMessage = new LayUITableMessage();
		layUITableMessage.setData(list);
		return layUITableMessage;
	}

}
