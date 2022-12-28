package com.wmcfrs.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.UserDao;
import com.wmcfrs.model.User;
import com.wmcfrs.util.PageBean;

/**
 * 用户业务类
 */
@Transactional
public class UserService {

	//初始化数据层对象  
	private UserDao userDao;
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<User> list(PageBean<User> pageBean,Map<String,Object[]> params){
		return userDao.list(pageBean, params);
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public Integer save(User user) {
		if(null!=userDao.findByUserName(user.getUsername())){
			return -1;//用户名已存在
		}
		return userDao.save(user);
	}
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user){
		return userDao.login(user);
	}
	
	/**
	 * 通过主键查找用户
	 * @param id
	 * @return
	 */
	public User findById(Integer id){
		return userDao.findById(id);
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user) {
		userDao.update(user);
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public Integer update(User user,User cUser) {
		User searchUser = userDao.findByUserName(user.getUsername());
		if(null!= searchUser && !user.getUsername().equals(cUser.getUsername())){
			return -1;//用户名已存在
		}
		searchUser.setEmail(user.getEmail());
		searchUser.setUsername(user.getUsername());
		searchUser.setHeader(user.getHeader());
		
		userDao.update(searchUser);
		return 1;
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return userDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		userDao.delete(id);
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
