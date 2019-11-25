package net.hycollege.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hycollege.mapper.UserMapper;
import net.hycollege.mybatis.domain.User;
import net.hycollege.mybatis.domain.UserExample;
import net.hycollege.mybatis.domain.UserExample.Criteria;
import net.hycollege.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired(required = true)
	private UserMapper userMapper;

	@Override
	public User select(String username, String password) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username).andPasswordEqualTo(MD5Encoder.encode(password.getBytes()));
		List<User> users = userMapper.selectByExample(userExample);
		if (users == null || users.size() != 1)
			return null;
		return users.get(0);
	}

	@Override
	public void insert(User user) {
		user.setUid(UUID.randomUUID().toString());
		String password = user.getPassword();
		password = MD5Encoder.encode(password.getBytes());
		user.setPassword(password);
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKey(user);

	}
}
