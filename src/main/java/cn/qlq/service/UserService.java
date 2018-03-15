package cn.qlq.service;

import java.sql.SQLException;
import java.util.List;

import cn.qlq.bean.User;

public interface UserService {

	/**
	 * 通过id查询用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(int id) throws Exception;

	/**
	 * 分页查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<User> findUsersByPage(String name) throws SQLException;

	/**
	 * 插入User
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int addUser(int id, String name) throws SQLException;

	/**
	 * 根据编号删除缓存
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteById(int id) throws SQLException;
}
