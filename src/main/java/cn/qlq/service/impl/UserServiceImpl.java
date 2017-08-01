package cn.qlq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qlq.bean.User;
import cn.qlq.mapper.UserMapper;
import cn.qlq.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public User findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findUserById(id);
	}

}
