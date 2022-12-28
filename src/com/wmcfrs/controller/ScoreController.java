package com.wmcfrs.controller;

import com.wmcfrs.model.Music;
import com.wmcfrs.model.Score;
import com.wmcfrs.model.User;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.service.ScoreService;
import com.wmcfrs.util.PageBean;

/**
 * 前台评分控制器
 */
public class ScoreController extends BaseController{

	private static final long serialVersionUID = 1L;
	//评分service
	private ScoreService scoreService;
	//收藏service
	private CollectionService collectionService;
	//分页
	private PageBean<Score> pageBean = new PageBean<Score>();
	
	/**
	 * 评分分页列表
	 * @return
	 */
	public String list(){
		//定义查询参数
		params.put("user.id",new Object[]{"=",getCurrentUser().getId()});
		pageBean = scoreService.list(pageBean, params);
		return SUCCESS;
	}
	
	/**
	 * 添加评分记录，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String addScore(){
		String point = request.getParameter("point");
		String musicid = request.getParameter("musicid");
		String url = request.getParameter("url");
		User user = getCurrentUser();
		Music music = new Music();
		music.setId(Integer.parseInt(musicid));
		Score score = new Score();
		score.setMusic(music);
		score.setUser(user);
		score.setPoint(Integer.parseInt(point));
		scoreService.save(score);
		resultMap.put("success",1);
		if(url!=null && !url.equals("")){
			resultMap.put("url",url);
		}
		return SUCCESS;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setPageBean(PageBean<Score> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Score> getPageBean() {
		return pageBean;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
}
