package com.wmcfrs.service;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.AdminDao;
import com.wmcfrs.model.Admin;

/**
 * 管理员业务类
 */
@Transactional
public class AdminService {

	//初始化管理员数据层对象
	private AdminDao adminDao;
	
	/**
	 * 登录
	 * @param Admin
	 * @return
	 */
	public Admin login(Admin admin){
		return adminDao.login(admin);
	}
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
}
