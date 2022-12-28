package com.wmcfrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.CollectionDao;
import com.wmcfrs.model.Collection;
import com.wmcfrs.util.PageBean;

/**
 * 收藏业务类
 */
@Transactional
public class CollectionService {

	//初始化数据层对象  
	private CollectionDao collectionDao;
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<Collection> list(PageBean<Collection> pageBean,Map<String,Object[]> params){
		return collectionDao.list(pageBean, params);
	}
	
	/**
	 * 保存
	 * @param collection
	 * @return
	 */
	public Integer save(Collection collection) {
		List<Collection> list = collectionDao.find(collection.getUser().getId(),
				collection.getMusic().getId());
		if(list!=null && list.size()>0){
			return 1;
		}
		return collectionDao.save(collection);
	}
	
	/**
	 * 删除
	 * @param collection
	 * @return
	 */
	public void delete(Collection collection) {
		collectionDao.delete(collection);
	}
	
	/**
	 * 查询
	 * @param userid
	 * @return
	 */
	public List<Collection> find(Integer userid){
		return collectionDao.find(userid);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return collectionDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		collectionDao.delete(id);
	}
	
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}
	
}
