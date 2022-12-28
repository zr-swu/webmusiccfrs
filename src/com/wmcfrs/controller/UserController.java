package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.User;
import com.wmcfrs.service.UserService;
import com.wmcfrs.util.Constant;

/**
 * 前台用户控制器
 */
public class UserController extends BaseController implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象，将从页面获取的数据直接封装在用户实体类中
	private User user = new User();
	//用户service 
	private UserService userService;
	
	/**
	 * 个人中心
	 * @return
	 */
	public String viewUser(){
		User cUser = getCurrentUser();
		request.setAttribute("user", userService.findById(cUser.getId()));
		return SUCCESS;
	}
	
	/**
	 * 跳转到用户编辑页面
	 * @return
	 */
	public String editUser(){
		User cUser = getCurrentUser();
		request.setAttribute("user", userService.findById(cUser.getId()));
		return SUCCESS;
	}
	
	/**
	 * 更新，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String updateUser(){
		User cUser = userService.findById(getCurrentUser().getId());
		user.setPassword(cUser.getPassword());
		user.setCreatetime(cUser.getCreatetime());
		userService.update(user);
		request.getSession().setAttribute(Constant.session_user, user);
		resultMap.put("success",1);
		resultMap.put("url","viewUser");
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	public String editPassword(){
		User cUser = getCurrentUser();
		request.setAttribute("user", userService.findById(cUser.getId()));
		return SUCCESS;
	}
	
	/**
	 * 修改密码，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String updatePassword(){
		String oldPassword = request.getParameter("oldPassword");
		User cUser = userService.findById(getCurrentUser().getId());
		int success = 1;
		if(cUser.getPassword().equals(oldPassword)){
			cUser.setPassword(user.getPassword());
			userService.update(cUser);
		}else{
			success = -1;//原密码不正确
		}
		resultMap.put("success",success);
		if(success>0){
			request.getSession().setAttribute(Constant.session_user, null);
			resultMap.put("url","login");
		}
		return SUCCESS;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public User getModel() {
		return user;
	}
	
}
