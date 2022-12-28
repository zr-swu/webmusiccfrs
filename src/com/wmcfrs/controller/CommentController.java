package com.wmcfrs.controller;

import java.net.URLDecoder;

import com.wmcfrs.model.Comment;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.User;
import com.wmcfrs.service.CommentService;
import com.wmcfrs.util.DateUtil;
import com.wmcfrs.util.PageBean;

/**
 * 前台评论控制器
 */
public class CommentController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	//评论service
	private CommentService commentService;
	//分页
	private PageBean<Comment> pageBean = new PageBean<Comment>();
	
	/**
	 * 评分分页列表
	 * @return
	 */
	public String list(){
		//定义查询参数
		params.put("user.id",new Object[]{"=",getCurrentUser().getId()});
		pageBean = commentService.list(pageBean, params);
		return SUCCESS;
	}
	
	/**
	 * 添加评论，ajax异步请求，返回json格式数据
	 * @return
	 * @throws Exception
	 */
	public String addComment() throws Exception{
		//获取评论内容，中文需要转码防止乱码
		String comment2 = request.getParameter("comment");
		comment2 = URLDecoder.decode(comment2, "UTF-8");
		String playlistid = request.getParameter("playlistid");
		User user = getCurrentUser();
		Playlist playlist = new Playlist();
		playlist.setId(Integer.parseInt(playlistid));
		Comment comment = new Comment();
		comment.setPlaylist(playlist);
		comment.setUser(user);
		comment.setComment(comment2);
		comment.setCreatetime(DateUtil.getCurrentDate());
		commentService.save(comment);
		resultMap.put("success",1);
		resultMap.put("url","playlistView?playlistid="+playlistid);//操作成功后，需要跳转的页面
		return SUCCESS;
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String commentid = request.getParameter("commentid");
		commentService.delete(Integer.parseInt(commentid));
		resultMap.put("success",1);
		resultMap.put("url","commentList");
		return SUCCESS;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public PageBean<Comment> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Comment> pageBean) {
		this.pageBean = pageBean;
	}
	
}
