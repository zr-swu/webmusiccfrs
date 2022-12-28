package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.User;
import com.wmcfrs.service.UserService;
import com.wmcfrs.util.DateUtil;
import com.wmcfrs.util.PageBean;

/**
 * 管理员用户控制器
 */
public class AdminUserController extends BaseController 
	implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	//实体
	private User user = new User();
	//用户service
	private UserService userService;
	//分页
	private PageBean<User> pageBean = new PageBean<User>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		String username = request.getParameter("username");
		params.put("username",new Object[]{"like",username});
		pageBean = userService.list(pageBean, params);
		request.setAttribute("username",username);
		return "listSuccess";
	}
	
	/**
	 * 跳转到添加或者修改页面
	 * @return
	 */
	public String addOrUpdate(){
		String userid = request.getParameter("userid");
		if(userid!=null && !"".equals(userid)){
			User cUser = userService.findById(Integer.parseInt(userid));
			request.setAttribute("user",cUser);
		}
		return "addOrUpdateSuccess";
	}
	
	/**
	 * 添加或者更新，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doAddOrUpdate(){
		int success = 0;
		if(user.getId()!=null){
			User cUser = userService.findById(user.getId());
			user.setCreatetime(cUser.getCreatetime());
			user.setPassword(cUser.getPassword());
			success = userService.update(user,cUser);
		}else{
			user.setCreatetime(DateUtil.getCurrentDate());
			user.setPassword("123456");
			success = userService.save(user);
			if(success==1){
				resultMap.put("msg","操作成功！初始密码：123456");
			}
		}
		if(success==-1){
			resultMap.put("msg","用户名已存在！");
		}
		resultMap.put("success",success);
		resultMap.put("url","admin_user_list");
		return "doAddOrUpdateSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String userid = request.getParameter("userid");
		userService.delete(Integer.parseInt(userid));
		resultMap.put("success",1);
		resultMap.put("url","admin_user_list");
		return "deleteSuccess";
	}
	
	public User getModel() {
		return user;
	}
	
	public void setPageBean(PageBean<User> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<User> getPageBean() {
		return pageBean;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}