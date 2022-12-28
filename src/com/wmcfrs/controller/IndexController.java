package com.wmcfrs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mahout.cf.taste.model.DataModel;

import com.wmcfrs.model.Music;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.Score;
import com.wmcfrs.model.User;
import com.wmcfrs.service.CollectionService;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;
import com.wmcfrs.service.ScoreService;
import com.wmcfrs.util.CFUtilEx;
import com.wmcfrs.util.PageBean;

/**
 * 前台首页控制器
 */
public class IndexController extends BaseController{

	private static final long serialVersionUID = 1L;
	//歌单service
	private PlaylistService playlistService;
	//音乐service
	private MusicService musicService;
	//歌单音乐service
	private RecordService recordService;
	//收藏service
	private CollectionService collectionService;
	//评分service
	private ScoreService scoreService;
	//分页
	private PageBean<Playlist> pageBean = new PageBean<Playlist>();
	
	/**
	 * 首页
	 * 个性化推荐
	 * 首先使用基于项目的协同过滤推荐算法进行推荐
	 * 如果基于项目的协同过滤推荐算法没有推荐结果，进行热点推荐
	 * @return
	 */
	public String index(){
		//分页查询歌单
		pageBean = playlistService.list(pageBean, params);
		//当前用户
		User cUser = getCurrentUser();
		//如果用户没有登录
		if(cUser==null){
			//热点推荐（根据收藏量降序排列推荐）
			List<Music> hotMusic = musicService.findHot();
			request.setAttribute("hotMusic", hotMusic);
		}else{
			//协同过滤推荐
			//查找所有用户评分
			List<Score> scoreList = scoreService.find();
			//定义协同过滤工具类
			CFUtilEx cfUtilEx = new CFUtilEx();
			//获取用户-项目评分矩阵
			DataModel model = cfUtilEx.getDadaModel(cUser, scoreList);
			//执行协同过滤方法，得到推荐map结果（基于项目的协同过滤推荐算法）
			Map<Integer,Float> cfMap = cfUtilEx.baseItem(cUser, model);
			//定义推荐音乐集合
			List<Music> cfMusicList = null;
			if(cfMap!=null && cfMap.size()>0){
				Set<Integer> set = cfMap.keySet();
				String cfmusicids = "";
				for(Integer cfmusicid:set){
					cfmusicids+=cfmusicid+",";
				}
				cfmusicids = cfmusicids.substring(0,cfmusicids.lastIndexOf(","));
				cfMusicList = musicService.find(cfmusicids);
			}else{
				System.out.println("基于项目的协同过滤推荐算法没有推荐结果，进行热点推荐");
				//热点推荐（根据收藏量降序排列推荐）
				cfMusicList = musicService.findHot();
			}
			request.setAttribute("cfMusicList", cfMusicList);
		}
		return SUCCESS;
	}
	
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}
	
	public void setPageBean(PageBean<Playlist> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Playlist> getPageBean() {
		return pageBean;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
}
