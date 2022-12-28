package com.wmcfrs.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmcfrs.model.User;
import com.wmcfrs.util.PageBean;
import com.wmcfrs.util.SqlUtil;

/**
 * 用户dao（基础的增删改查操作）
 */
public class UserDao extends HibernateDaoSupport{

	/**
	 * 分页查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<User> list(PageBean<User> pageBean,Map<String,Object[]> params){
		Session session = this.getSession(true);
		Criteria criteria = session.createCriteria(User.class);
		criteria = new SqlUtil().setSqlParams(criteria,params);//设置参数
		//先查询记录总数
		Long totalCount = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		pageBean.setTotalCount(Integer.parseInt(totalCount.toString()));
		criteria.setProjection(null);//设置为空，可进行正常分页
		criteria.setFirstResult((pageBean.getPage()-1)*pageBean.getLimit());//首页
		criteria.setMaxResults(pageBean.getLimit());//查询数据条数
		criteria.addOrder(Order.desc("id"));//排序
		pageBean.setList(criteria.list());//结果集合
		return pageBean;
	}
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public Integer save(User user) {
		Integer id = (Integer) this.getHibernateTemplate().save(user);
		return id;
	}
	/**
	 * 通过用户登录名查找用户，注册的时候验证用户名是否已存在
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User findByUserName(String userName){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, userName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User login(User user) {
		String hql = "from User where username = ? and password = ? ";
		List<User> list = this.getHibernateTemplate().find(hql,
				user.getUsername(), user.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 通过主键查找用户
	 * @param id
	 * @return
	 */
	public User findById(Integer id){
		return this.getHibernateTemplate().get(User.class, id);
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		Session session = this.getSession();
		return (Long) session.createQuery("select count(*) from User").uniqueResult();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete User where id="+id).executeUpdate();
	}
	
}
