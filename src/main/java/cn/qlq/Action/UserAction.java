package cn.qlq.Action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.opensymphony.xwork2.ActionSupport;

import cn.qlq.bean.User;
import cn.qlq.service.UserService;

@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class UserAction extends ActionSupport {
	private Map<String, Object> response;
	@Autowired
	private UserService userService;
	private int id;
	private String name;

	/**
	 * 测试清除注解保存的缓存的同时手动添加单个缓存
	 * 
	 * @return
	 */
	public String add() {
		try {
			userService.addUser(id, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}

	/**
	 * 测试查询单个的时候注解添加单个缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String find() throws Exception {
		User user = userService.findUserById(id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("user", user);
		return "find";
	}

	
	/**
	 * 测试删除单个的时候注解删除单个缓存
	 * @return
	 */
	public String delete() {
		try {
			userService.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "delete";
	}
	
	/**
	 * 测试注解缓存，将查出来的集合结果加入缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPage() throws Exception {
		response = new HashMap();
		if (name == null)
			name = "111";
		// 第三个参数代表排序方式
		PageHelper.startPage(2, 2, "id desc");
		List<User> users = userService.findUsersByPage(name);
		response.put("users", users);
		return "success";
	}

	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
