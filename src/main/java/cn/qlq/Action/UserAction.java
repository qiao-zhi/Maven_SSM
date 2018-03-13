package cn.qlq.Action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public String add() {
		try {
			userService.addUser(id, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}

	public String delete() {
		return "delete";
	}

	public String update() {
		return "update";
	}

	public String find() throws Exception {
		User user = userService.findUserById(1);
		System.out.println(user);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("user", user);
		return "find";
	}

	public String findPage() throws Exception {
		response = new HashMap();
		// 第三个参数代表排序方式
		PageHelper.startPage(2, 2, "id desc");
		List<User> users = userService.findUsersByPage();
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
