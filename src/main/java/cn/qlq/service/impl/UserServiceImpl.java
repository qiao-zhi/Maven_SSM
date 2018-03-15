package cn.qlq.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.qlq.bean.User;
import cn.qlq.mapper.UserMapper;
import cn.qlq.service.UserService;
import cn.qlq.util.RedisUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserMapper userMapper;
	
	
	//查询出来的时候添加单个缓存
	@Cacheable(value="user",key="'user'+#id.toString()")
	@Override
	public User findUserById(int id) throws Exception {
		System.out.println("打印语句则没有走缓存");
		return userMapper.findUserById(id);
	}
	
	//删除数据库的时候删除redis的缓存
	@Override
	@CacheEvict(value="user",key="'user'+#id.toString()")
	public boolean deleteById(int id){
		return true;
	}
	
	
	//添加缓存
	@Cacheable(value="Alluser")
	@Override
	public List<User> findUsersByPage(String name) throws SQLException {
		System.out.println("打印语句则没有走缓存");
		return userMapper.findUsersByPage();
	}
	
	//清除上面的缓存，同时手动的添加一个缓存看能否实现
	@CacheEvict(value="Alluser")
	@Override
	public int addUser(int id, String name) throws SQLException {
		redisUtil.set("mykey", "myvalue");
		return userMapper.addUser(id, name);
	}

}
