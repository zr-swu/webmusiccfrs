package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Score;
import com.wmcfrs.service.ScoreService;
import com.wmcfrs.util.PageBean;

/**
 * 管理员评分控制器
 */
public class AdminScoreController extends BaseController 
	implements ModelDriven<Score>{

	private static final long serialVersionUID = 1L;
	//实体
	private Score score = new Score();
	//评分service
	private ScoreService scoreService;
	//分页
	private PageBean<Score> pageBean = new PageBean<Score>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		pageBean = scoreService.list(pageBean, params);
		return "listSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String scoreid = request.getParameter("scoreid");
		scoreService.delete(Integer.parseInt(scoreid));
		resultMap.put("success",1);
		resultMap.put("url","admin_score_list");
		return "deleteSuccess";
	}
	
	public Score getModel() {
		return score;
	}
	
	public void setPageBean(PageBean<Score> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Score> getPageBean() {
		return pageBean;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
}