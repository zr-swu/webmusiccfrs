package com.wmcfrs.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.RecordDao;
import com.wmcfrs.model.Record;

/**
 * 歌单音乐业务类
 */
@Transactional
public class RecordService {

	//初始化数据层对象  
	private RecordDao recordDao;
	
	/**
	 * 查找所有评分记录
	 * @return
	 */
	public List<Record> find(){
		return recordDao.find();
	}
	
	/**
	 * 查找评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	public Record find(Integer userid,Integer musicid){
		return recordDao.find(userid, musicid);
	}
	
	/**
	 * 批量插入
	 * @param list
	 */
	public void save(List<Record> list){
		recordDao.save(list);
	}
	
	/**
	 * 根据歌单id查找记录
	 * @param playlistid
	 * @return
	 */
	public List<Record> find(Integer playlistid){
		return recordDao.find(playlistid);
	}
	
	/**
	 * 查找记录最后一条数据(id最大)
	 * @return
	 */
	public Record findLast(){
		return recordDao.findLast();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		recordDao.delete(id);
	}
	
	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	
}
