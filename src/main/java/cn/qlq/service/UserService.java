package cn.qlq.service;

import cn.qlq.bean.User;

public interface UserService {

	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(int id) throws Exception;
}
