package net.hycollege.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hycollege.mapper.AdminMapper;
import net.hycollege.message.bean.Message;
import net.hycollege.mybatis.domain.Admin;
import net.hycollege.mybatis.domain.AdminExample;
import net.hycollege.mybatis.domain.AdminExample.Criteria;
import net.hycollege.services.AdminServices;
@Service
public class AdminServicesImpl implements AdminServices {
	@Autowired(required = true)
	private AdminMapper adminMapper;

	@Override
	public Message select(String username, String password) {
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<Admin> list = adminMapper.selectByExample(example);
		if (list != null && list.size() == 1) {
			return new Message(Message.ok);
		}
		return new Message(Message.fail);
	}

}
