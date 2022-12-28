package com.wmcfrs.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wmcfrs.model.Collection;
import com.wmcfrs.util.PageBean;
import com.wmcfrs.util.SqlUtil;

/**
 * 收藏dao（基础的增删改查操作）
 */
public class CollectionDao extends HibernateDaoSupport{

	/**
	 * 分页查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Collection> list(PageBean<Collection> pageBean,Map<String,Object[]> params){
		Session session = this.getSession(true);
		Criteria criteria = session.createCriteria(Collection.class);
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
	 * 保存
	 * @param collection
	 * @return
	 */
	public Integer save(Collection collection) {
		Integer id = (Integer) this.getHibernateTemplate().save(collection);
		return id;
	}
	
	/**
	 * 查询
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Collection> find(Integer userid){
		return this.getHibernateTemplate().find("from Collection where userid = ? ",userid);
	}
	
	/**
	 * 查询
	 * @param userid
	 * @param musicid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Collection> find(Integer userid,Integer musicid){
		return this.getHibernateTemplate().find("from Collection where userid = ? and musicid = ?",userid,musicid);
	}
	
	/**
	 * 删除
	 * @param collection
	 * @return
	 */
	public void delete(Collection collection) {
		this.getHibernateTemplate().delete(collection);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		Session session = this.getSession();
		return (Long) session.createQuery("select count(*) from Collection").uniqueResult();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete Collection where id="+id).executeUpdate();
	}
	
}
