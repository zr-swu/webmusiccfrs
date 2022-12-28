package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Collection;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.util.PageBean;

/**
 * 管理员收藏控制器
 */
public class AdminCollectionController extends BaseController 
	implements ModelDriven<Collection>{

	private static final long serialVersionUID = 1L;
	//实体
	private Collection collection = new Collection();
	//收藏service
	private CollectionService collectionService;
	//分页
	private PageBean<Collection> pageBean = new PageBean<Collection>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		pageBean = collectionService.list(pageBean, params);
		return "listSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String collectionid = request.getParameter("collectionid");
		collectionService.delete(Integer.parseInt(collectionid));
		resultMap.put("success",1);
		resultMap.put("url","admin_collection_list");
		return "deleteSuccess";
	}
	
	public Collection getModel() {
		return collection;
	}
	
	public void setPageBean(PageBean<Collection> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Collection> getPageBean() {
		return pageBean;
	}

	public CollectionService getCollectionService() {
		return collectionService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
}