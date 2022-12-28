package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Admin;
import com.wmcfrs.service.AdminService;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.service.CommentService;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;
import com.wmcfrs.service.ScoreService;
import com.wmcfrs.service.UserService;
import com.wmcfrs.util.Constant;

/**
 * 管理员控制器
 */
public class AdminController extends BaseController implements ModelDriven<Admin>{

	private static final long serialVersionUID = 1L;
	//实体
	private Admin admin = new Admin();
	//管理员service
	private AdminService adminService;
	//音乐service
	private MusicService musicService;
	//用户service
	private UserService userService;
	//歌单service
	private PlaylistService playlistService;
	//评分service
	private ScoreService scoreService;
	//收藏service
	private CollectionService collectionService;
	//评论service
	private CommentService commentService;
	//歌单音乐service
	private RecordService recordService;
	
	/**
	 * 首页
	 * @return
	 */
	public String index(){
		return "indexSuccess";
	}
	
	/**
	 * 内容
	 * @return
	 */
	public String content(){
		request.setAttribute("userCount",userService.findCount());//用户数量
		request.setAttribute("playlistCount",playlistService.findCount());//歌单数量
		request.setAttribute("musicCount",musicService.findCount());//音乐数量
		request.setAttribute("scoreCount",scoreService.findCount());//评分数量
		request.setAttribute("collectionCount",collectionService.findCount());//收藏数量
		request.setAttribute("commentCount",commentService.findCount());//评论数量
		return "contentSuccess";
	}
	
	/**
	 * 跳转到后台登录页面
	 * @return
	 */
	public String login(){
		return "loginSuccess";
	}
	
	/**
	 * 登录，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doLogin(){
		Admin cAdmin = adminService.login(admin);
		int success;
		String url = "";
		if(cAdmin==null){//用户名或密码错误
			success = 0;
		}else{
			success = 1;
			url = "admin_index_index";
			request.getSession().setAttribute(Constant.session_admin,cAdmin);
		}
		resultMap.put("success",success);
		resultMap.put("url",url);
		return "doLoginSuccess";
	}
	
	/**
	 * 注销
	 * @return
	 */
	public String quit(){
		request.getSession().setAttribute(Constant.session_admin,null);
		return "quitSuccess";
	}

	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public Admin getModel() {
		return admin;
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	

}
