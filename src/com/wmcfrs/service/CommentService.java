package com.wmcfrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wmcfrs.dao.CommentDao;
import com.wmcfrs.model.Comment;
import com.wmcfrs.util.PageBean;

/**
 * 评论业务类
 */
@Transactional
public class CommentService {

	//初始化数据层对象  
	private CommentDao commentDao;
	
	/**
	 * 分页查询
	 * @return
	 */
	public PageBean<Comment> list(PageBean<Comment> pageBean,Map<String,Object[]> params){
		return commentDao.list(pageBean, params);
	}
	
	/**
	 * 保存
	 * @param comment
	 * @return
	 */
	public Integer save(Comment comment) {
		return commentDao.save(comment);
	}
	
	/**
	 * 查询
	 * @param musicid
	 * @return
	 */
	public List<Comment> find(Integer playlistId){
		return commentDao.find(playlistId);
	}
	
	/**
	 * 聚合查询
	 * @return
	 */
	public Long findCount(){
		return commentDao.findCount();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		commentDao.delete(id);
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
}
