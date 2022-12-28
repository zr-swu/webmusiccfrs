package com.wmcfrs.controller;

import java.util.List;

import net.sf.json.JSONArray;

import com.wmcfrs.model.Collection;
import com.wmcfrs.model.Music;
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
 * 前台音乐控制器
 */
public class MusicController extends BaseController{
	
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
	//分页
	private PageBean<Music> pageBean = new PageBean<Music>();
	
	/**
	 * 分页列表
	 * @return
	 */
	public String list(){
		//音乐名称查询参数
		String musicname = request.getParameter("musicname");
		//音乐类型查询参数
		params.put("musicname",new Object[]{"like",musicname});
		pageBean = musicService.list(pageBean, params);
		request.setAttribute("musicname", musicname);
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
			List<Collection> collectionList = collectionService.find(user.getId());
			request.setAttribute("collectionList", collectionList);
		}
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
	
	public void setPageBean(PageBean<Music> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Music> getPageBean() {
		return pageBean;
	}
	
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
}
