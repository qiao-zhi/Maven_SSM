package cn.qlq.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.qlq.bean.User;
import cn.qlq.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport{

	@Autowired
	private UserService userService;
	public String add(){
		return "add";
	}
	public String delete(){
		return "delete";
	}
	public String update(){
		return "update";
	}
	public String find() throws Exception{
		User user = userService.findUserById(1);
		System.out.println(user);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("user", user);
		return "find";
	}
}
