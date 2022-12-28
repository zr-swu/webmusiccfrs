package com.wmcfrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.ScoreDao;
import com.wmcfrs.model.Score;
import com.wmcfrs.util.PageBean;

/**
 * 音乐评分业务类
 */
@Transactional
public class ScoreService {

	//初始化数据层对象  
	private ScoreDao scoreDao;
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<Score> list(PageBean<Score> pageBean,Map<String,Object[]> params){
		return scoreDao.list(pageBean, params);
	}
	
	/**
	 * 保存
	 * @param score
	 * @return
	 */
	public Integer save(Score score) {
		Score score2 = scoreDao.find(score.getUser().getId(),score.getMusic().getId());
		if(score2==null){
			return scoreDao.save(score);
		}
		score2.setPoint(score.getPoint());
		scoreDao.update(score2);
		return 1;
	}
	
	/**
	 * 查找评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	public Score find(Integer userid,Integer musicid){
		return scoreDao.find(userid, musicid);
	}
	
	/**
	 * 更新评分记录
	 * @param score
	 * @return
	 */
	public void update(Score score){
		scoreDao.update(score);
	}
	
	/**
	 * 查找所有评分记录
	 * @return
	 */
	public List<Score> find(){
		return scoreDao.find();
	}
	
	/**
	 * 查找某个用户的所有评分记录
	 * @param userid
	 * @param musicid
	 * @return
	 */
	public List<Score> findAllByUserid(Integer userid){
		return scoreDao.findAllByUserid(userid);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return scoreDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		scoreDao.delete(id);
	}
	
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}
	
}
