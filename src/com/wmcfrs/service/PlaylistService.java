package com.wmcfrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.PlaylistDao;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.util.PageBean;

/**
 * 歌单业务类
 */
@Transactional
public class PlaylistService {

	//初始化数据层对象  
	private PlaylistDao playlistDao;
	
	/**
	 * 查找所有类型
	 * @return
	 */
	public List<Playlist> find(){
		return playlistDao.find();
	}
	/**
	 * 查找
	 * @return
	 */
	public List<Playlist> find(String playlistIds){
		return playlistDao.find(playlistIds);
	}
	
	/**
	 * 保存
	 * @param playlist
	 */
	public void save(Playlist playlist){
		playlistDao.save(playlist);
	}
	
	/**
	 * 更新
	 */
	public void update(Playlist playlist){
		playlistDao.update(playlist);
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<Playlist> list(PageBean<Playlist> pageBean,Map<String,Object[]> params){
		return playlistDao.list(pageBean, params);
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Playlist findById(Integer id){
		return playlistDao.findById(id);
	}
	
	/**
	 * 查找歌单最后一条数据(id最大)
	 * @return
	 */
	public Playlist findLast(){
		return playlistDao.findLast();
	}
	
	/**
	 * 根据歌单标题查找
	 * @return
	 */
	public List<Playlist> findByTitle(String title){
		return playlistDao.findByTitle(title);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return playlistDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		playlistDao.delete(id);
	}
	
	public void setPlaylistDao(PlaylistDao playlistDao) {
		this.playlistDao = playlistDao;
	}
	
}
