package com.wmcfrs.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wmcfrs.model.Score;
import com.wmcfrs.util.PageBean;
import com.wmcfrs.util.SqlUtil;

/**
 * 评分记录dao（基础的增删改查操作）
 */
public class ScoreDao extends HibernateDaoSupport{

	/**
	 * 保存
	 * @param score
	 * @return
	 */
	public Integer save(Score score) {
		Integer id = (Integer) this.getHibernateTemplate().save(score);
		return id;
	}
	
	/**
	 * 查找评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Score find(Integer userid,Integer musicid){
		List<Score> list = this.getHibernateTemplate().find("from Score where userid = ? and musicid = ?",
				userid,musicid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查找某个用户的所有评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Score> findAllByUserid(Integer userid){
		return this.getHibernateTemplate().find("from Score where userid = ? ",
				userid);
	}
	
	/**
	 * 更新评分记录
	 * @param score
	 * @return
	 */
	public void update(Score score){
		this.getHibernateTemplate().update(score);
	}
	
	/**
	 * 查找所有评分记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Score> find(){
		return this.getHibernateTemplate().find("from Score ");
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Score> list(PageBean<Score> pageBean,Map<String,Object[]> params){
		Session session = this.getSession(true);
		Criteria criteria = session.createCriteria(Score.class);
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
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		Session session = this.getSession();
		return (Long) session.createQuery("select count(*) from Score").uniqueResult();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete Score where id="+id).executeUpdate();
	}
	
}
