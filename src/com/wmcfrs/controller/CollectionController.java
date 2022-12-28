package com.wmcfrs.controller;

import com.wmcfrs.model.Collection;
import com.wmcfrs.model.Music;
import com.wmcfrs.model.User;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.util.PageBean;

/**
 * 前台收藏控制器
 */
public class CollectionController extends BaseController{

	private static final long serialVersionUID = 1L;
	//收藏service
	private CollectionService collectionService;
	//分页
	private PageBean<Collection> pageBean = new PageBean<Collection>();
	
	/**
	 * 收藏分页列表
	 * @return
	 */
	public String list(){
		//定义查询参数
		params.put("user.id",new Object[]{"=",getCurrentUser().getId()});
		pageBean = collectionService.list(pageBean, params);
		return SUCCESS;
	}
	
	/**
	 * 添加/取消收藏，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String addCollection(){
		//是收藏还是取消收藏
		String playlistid = request.getParameter("playlistid");
		String musicid = request.getParameter("musicid");
		String collectionid = request.getParameter("collectionid");
		String url = request.getParameter("url");
		Collection collection = new Collection();
		if(collectionid!=null && !collectionid.equals("")){
			collection.setId(Integer.parseInt(collectionid));
			collectionService.delete(collection);
		}else{
			User user = getCurrentUser();
			Music music = new Music();
			music.setId(Integer.parseInt(musicid));
			collection.setMusic(music);
			collection.setUser(user);
			collectionService.save(collection);
		}
		resultMap.put("success",1);//返回json格式数据，因为是ajax异步请求，1操作成功
		if(url!=null && !url.equals("")){
			resultMap.put("url",url);
		}else{
			resultMap.put("url","playlistView?playlistid="+playlistid);//返回json格式数据，因为是ajax异步请求，1操作成功
		}
		return SUCCESS;
	}
	
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public void setPageBean(PageBean<Collection> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Collection> getPageBean() {
		return pageBean;
	}
	
}
