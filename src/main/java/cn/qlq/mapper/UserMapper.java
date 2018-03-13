package cn.qlq.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mysql.jdbc.SQLError;

import cn.qlq.bean.User;

public interface UserMapper {

	/**
	 * 通过id查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User findUserById(int id);

	/**
	 * 分页查询用户信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> findUsersByPage() throws SQLException;

	// @Insert("insert into user values(#{0},#{1})")
	public int addUser(int id, String name) throws SQLException;

}
