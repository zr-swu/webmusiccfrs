package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.User;
import com.wmcfrs.service.UserService;
import com.wmcfrs.util.Constant;

/**
 * 前台登录控制器
 */
public class LoginController extends BaseController implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象，将从页面获取的数据直接封装在用户实体类中
	private User user = new User();
	//用户UserService 
	private UserService userService;
	
	/**
	 * 跳转到用户登录页面
	 * @return
	 */
	public String login(){
		return SUCCESS;
	}
	
	/**
	 * 登录，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doLogin(){
		User cUser = userService.login(user);
		int success;
		String url = "";
		if(cUser==null){//用户名或密码错误
			success = 0;
		}else{
			success = 1;
			url = "index";
			request.getSession().setAttribute(Constant.session_user,cUser);
		}
		resultMap.put("success",success);
		resultMap.put("url",url);
		return "doLoginSuccess";
	}
	
	/**
	 * 用户退出的方法
	 * @return
	 */
	public String quit(){
		// 销毁session
		request.getSession().setAttribute(Constant.session_user, null);
		return "quitSuccess";
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public User getModel() {
		return user;
	}
	
}
