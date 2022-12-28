package com.wmcfrs.controller;

import java.util.List;

import net.sf.json.JSONArray;

import com.wmcfrs.model.Collection;
import com.wmcfrs.model.Comment;
import com.wmcfrs.model.Music;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.Record;
import com.wmcfrs.model.Score;
import com.wmcfrs.model.User;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.service.CommentService;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;
import com.wmcfrs.service.ScoreService;
import com.wmcfrs.util.PageBean;

/**
 * 前台歌单控制器
 */
public class PlaylistController extends BaseController{

	private static final long serialVersionUID = 1L;
	//音乐service
	private MusicService musicService;
	//歌单service
	private PlaylistService playlistService;
	//收藏service
	private CollectionService collectionService;
	//歌单音乐service
	private RecordService recordService;
	//评分service
	private ScoreService scoreService;
	//评论service
	private CommentService commentService;
	//歌单分页
	private PageBean<Playlist> pageBean = new PageBean<Playlist>();
	//评论分页
	private PageBean<Comment> pageBeanEx = new PageBean<Comment>();
	
	/**
	 * 分页列表
	 * @return
	 */
	public String list(){
		pageBean = playlistService.list(pageBean, params);
		return SUCCESS;
	}
	
	/**
	 * 歌单详情
	 * @return
	 */
	public String view(){
		String playlistId = request.getParameter("playlistid");
		Playlist playlist = playlistService.findById(Integer.parseInt(playlistId));
		User user = getCurrentUser();
		if(user!=null){
			//查找当前用户评分过的音乐
			List<Score> scoreList = scoreService.findAllByUserid(user.getId());
			if(scoreList!=null && scoreList.size()>0){
				JSONArray jsonArray = new JSONArray();
				for(Score scoreTemp:scoreList){
					Score score = new Score();
					score.setId(scoreTemp.getId());
					Music music = new Music();
					music.setId(scoreTemp.getMusic().getId());
					score.setMusic(music);
					User user2 = new User();
					user2.setId(scoreTemp.getUser().getId());
					score.setUser(user2);
					score.setPoint(scoreTemp.getPoint());
					jsonArray.add(score);
				}
				request.setAttribute("scoreList", jsonArray);
			}
			//当前用户收藏列表
			List<Collection> collectionList = collectionService.find(user.getId());
			request.setAttribute("collectionList", collectionList);
		}
		//歌单中的音乐列表
		List<Record> recordList = recordService.find(Integer.parseInt(playlistId));
		//歌单评论
		params.clear();
		params.put("playlist.id",new Object[]{"=",Integer.parseInt(playlistId)});
		pageBeanEx = commentService.list(pageBeanEx,params);
		request.setAttribute("recordList", recordList);
		request.setAttribute("playlist", playlist);
		return SUCCESS;
	}
	
	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	public void setPageBean(PageBean<Playlist> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Playlist> getPageBean() {
		return pageBean;
	}
	
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public PageBean<Comment> getPageBeanEx() {
		return pageBeanEx;
	}

	public void setPageBeanEx(PageBean<Comment> pageBeanEx) {
		this.pageBeanEx = pageBeanEx;
	}
	
}
