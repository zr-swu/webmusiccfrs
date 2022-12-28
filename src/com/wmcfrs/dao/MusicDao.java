package com.wmcfrs.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmcfrs.model.Music;
import com.wmcfrs.util.PageBean;
import com.wmcfrs.util.SqlUtil;

/**
 * 音乐dao（基础的增删改查操作）
 */
public class MusicDao extends HibernateDaoSupport{

	/**
	 * 分页查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Music> list(PageBean<Music> pageBean,Map<String,Object[]> params){
		Session session = this.getSession(true);
		Criteria criteria = session.createCriteria(Music.class);
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
	 * @param music
	 * @return
	 */
	public Integer save(Music music) {
		Integer id = (Integer) this.getHibernateTemplate().save(music);
		return id;
	}
	
	/**
	 * 更新
	 * @param music
	 */
	public void update(Music music){
		this.getHibernateTemplate().update(music);
	}
	
	/**
	 * 通过音乐名查找音乐
	 * @param musicName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Music findByMusicName(String musicName){
		String hql = "from Music where musicname = ?";
		List<Music> list = this.getHibernateTemplate().find(hql, musicName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查找所有音乐
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Music> find(){
		String hql = "from Music";
		List<Music> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 查找音乐
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Music> find(String musicIds){
		String hql = "from Music where id in ("+musicIds+")";
		List<Music> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 查找音乐最后一条数据(id最大)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Music findLast(){
		String hql = "From Music order by id desc limit 0,1";
		List<Music> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 通过音乐id查询音乐
	 * @param id
	 * @return
	 */
	public Music findById(Integer id){
		return this.getHibernateTemplate().get(Music.class, id);
	}
	
	/**
	 * 查找热点音乐
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Music> findHot(){
		String hql = " SELECT m.* FROM music AS m " +
				"		JOIN collection AS c ON m.id = c.musicid " +
				"			GROUP BY c.musicid " +
				"			ORDER BY count(c.musicid) DESC limit 0,8";
		Session session = this.getSession(true);
		Query query  = session.createSQLQuery(hql).addEntity("m",Music.class);
		return query.list();
	}
	
	/**
	 * 批量插入
	 * @param list
	 */
	public void save(List<Music> list){
		Session session = this.getSession();
		for(int i=0;i<list.size();i++){
			session.save(list.get(i));
			if(i%100==0){	//每一千条刷新并写入数据库
		    	session.flush();
		    	session.clear();
		    }
		}
		session.flush();
    	session.clear();
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		Session session = this.getSession();
		return (Long) session.createQuery("select count(*) from Music").uniqueResult();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete Music where id="+id).executeUpdate();
	}
	
}