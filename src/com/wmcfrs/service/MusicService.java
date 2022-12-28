package com.wmcfrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.MusicDao;
import com.wmcfrs.model.Music;
import com.wmcfrs.util.PageBean;

/**
 * 音乐业务类
 */
@Transactional
public class MusicService {

	//初始化数据层对象  
	private MusicDao musicDao;
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<Music> list(PageBean<Music> pageBean,Map<String,Object[]> params){
		return musicDao.list(pageBean, params);
	}
	
	/**
	 * 通过音乐名查找音乐
	 * @param musicName
	 * @return
	 */
	public Music findByMusicName(String musicName){
		return musicDao.findByMusicName(musicName);
	}
	
	/**
	 * 更新
	 * @param music
	 */
	public void update(Music music){
		musicDao.update(music);
	}
	
	/**
	 * 查找所有音乐
	 * @return
	 */
	public List<Music> find(){
		return musicDao.find(); 
	}
	
	/**
	 * 查找热点音乐
	 * @return
	 */
	public List<Music> findHot(){
		return musicDao.findHot();
	}
	
	/**
	 * 通过音乐id查询音乐
	 * @param id
	 * @return
	 */
	public Music findById(Integer id){
		return musicDao.findById(id);
	}
	
	/**
	 * 保存
	 * @param music
	 * @return
	 */
	public Integer save(Music music) {
		return musicDao.save(music);
	}
	
	/**
	 * 批量插入
	 * @param list
	 */
	public void save(List<Music> list){
		musicDao.save(list);
	}
	
	/**
	 * 查找音乐
	 * @return
	 */
	public List<Music> find(String musicIds){
		return musicDao.find(musicIds);
	}
	
	/**
	 * 查找音乐最后一条数据(id最大)
	 * @return
	 */
	public Music findLast(){
		return musicDao.findLast();
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return musicDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		musicDao.delete(id);
	}
	
	public void setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
	}
	
}
