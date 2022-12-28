package com.wmcfrs.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmcfrs.model.Playlist;
import com.wmcfrs.util.PageBean;
import com.wmcfrs.util.SqlUtil;

/**
 * 歌单dao（基础的增删改查操作）
 */
public class PlaylistDao extends HibernateDaoSupport{

	/**
	 * 查找所有
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Playlist> find(){
		String hql = "from Playlist";
		List<Playlist> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 根据歌单标题查找
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Playlist> findByTitle(String title){
		String hql = "from Playlist where title=?";
		List<Playlist> list = this.getHibernateTemplate().find(hql,title);
		return list;
	}
	
	/**
	 * 查找
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Playlist> find(String playlistIds){
		String hql = "from Playlist where id in ("+playlistIds+")";
		List<Playlist> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 保存
	 * @param playlist
	 */
	public void save(Playlist playlist){
		this.getHibernateTemplate().save(playlist);
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Playlist> list(PageBean<Playlist> pageBean,Map<String,Object[]> params){
		Session session = this.getSession(true);
		Criteria criteria = session.createCriteria(Playlist.class);
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
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Playlist findById(Integer id){
		return this.getHibernateTemplate().get(Playlist.class, id);
	}
	
	/**
	 * 查找歌单最后一条数据(id最大)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Playlist findLast(){
		String hql = "From Playlist order by id desc limit 0,1";
		List<Playlist> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 更新
	 */
	public void update(Playlist playlist){
		this.getHibernateTemplate().update(playlist);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		Session session = this.getSession();
		return (Long) session.createQuery("select count(*) from Playlist").uniqueResult();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete Playlist where id="+id).executeUpdate();
	}
	
}
