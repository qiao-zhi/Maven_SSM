package cn.qlq.service.impl;

import java.sql.SQLException;
import java.util.List;

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
	@Override
	public List<User> findUsersByPage() throws SQLException {
		// TODO Auto-generated method stub
		return userMapper.findUsersByPage();
	}
	@Override
	public int addUser(int id, String name) throws SQLException {
		// TODO Auto-generated method stub
		return userMapper.addUser(id, name);
	}

}
