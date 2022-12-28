package com.wmcfrs.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmcfrs.model.Record;

/**
 * 歌单音乐dao（基础的增删改查操作）
 */
public class RecordDao extends HibernateDaoSupport{

	
	/**
	 * 查找评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Record find(Integer userid,Integer musicid){
		List<Record> list = this.getHibernateTemplate().find("from Record where userid = ? and musicid = ?",
				userid,musicid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查找所有评分记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Record> find(){
		return this.getHibernateTemplate().find("from Record ");
	}
	
	/**
	 * 根据歌单id查找记录
	 * @param playlistid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Record> find(Integer playlistid){
		return this.getHibernateTemplate().find("from Record where playlistid = ? ",
				playlistid);
	}
	
	/**
	 * 查找记录最后一条数据(id最大)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Record findLast(){
		String hql = "From Record order by id desc limit 0,1";
		List<Record> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 批量插入
	 * @param list
	 */
	public void save(List<Record> list){
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
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		Session session = this.getSession(true);
		session.createQuery("delete Record where id="+id).executeUpdate();
	}
	
}
