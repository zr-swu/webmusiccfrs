package com.wmcfrs.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmcfrs.model.Admin;

/**
 * 管理员dao（基础的增删改查操作）
 */
public class AdminDao  extends HibernateDaoSupport{
	
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Admin login(Admin admin) {
		String hql = "from Admin where adminname = ? and password = ? ";
		List<Admin> list = this.getHibernateTemplate().find(hql,
				admin.getAdminname(), admin.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
}