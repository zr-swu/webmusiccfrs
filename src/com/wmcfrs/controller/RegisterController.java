package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.User;
import com.wmcfrs.service.UserService;
import com.wmcfrs.util.DateUtil;

/**
 * 前台用户注册控制器
 */
public class RegisterController extends BaseController implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象，将从页面获取的数据直接封装在用户实体类中
	private User user = new User();
	//用户service 
	private UserService userService;
	
	/**
	 * 跳转到用户注册页面
	 * @return
	 */
	public String register(){
		return SUCCESS;
	}
	
	/**
	 * 注册，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doRegister(){
		user.setCreatetime(DateUtil.getCurrentDate());
		Integer success = userService.save(user);
		resultMap.put("success",success);
		resultMap.put("url","login");
		return "doRegisterSuccess";
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public User getModel() {
		return user;
	}
	
}
