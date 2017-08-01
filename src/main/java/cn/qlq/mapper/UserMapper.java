package cn.qlq.mapper;

import cn.qlq.bean.User;

public interface UserMapper {

	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	public User findUserById(int id);
}
