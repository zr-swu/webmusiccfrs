package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Comment;
import com.wmcfrs.service.CommentService;
import com.wmcfrs.util.PageBean;

/**
 * 管理员评论控制器
 */
public class AdminCommentController extends BaseController 
	implements ModelDriven<Comment>{

	private static final long serialVersionUID = 1L;
	//实体
	private Comment comment = new Comment();
	//评论service
	private CommentService commentService;
	//分页
	private PageBean<Comment> pageBean = new PageBean<Comment>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		pageBean = commentService.list(pageBean, params);
		return "listSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String commentid = request.getParameter("commentid");
		commentService.delete(Integer.parseInt(commentid));
		resultMap.put("success",1);
		resultMap.put("url","admin_comment_list");
		return "deleteSuccess";
	}
	
	public Comment getModel() {
		return comment;
	}
	
	public void setPageBean(PageBean<Comment> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Comment> getPageBean() {
		return pageBean;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
}