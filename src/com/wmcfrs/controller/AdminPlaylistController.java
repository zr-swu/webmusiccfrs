package com.wmcfrs.controller;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.Record;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;
import com.wmcfrs.util.PageBean;

/**
 * 管理员歌单控制器
 */
public class AdminPlaylistController extends BaseController 
	implements ModelDriven<Playlist>{

	private static final long serialVersionUID = 1L;
	//实体
	private Playlist playlist = new Playlist();
	//歌单service
	private PlaylistService playlistService;
	//音乐service
	private MusicService musicService;
	//歌单音乐service
	private RecordService recordService;
	//分页
	private PageBean<Playlist> pageBean = new PageBean<Playlist>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		String title = request.getParameter("title");
		params.put("title",new Object[]{"like",title});
		pageBean = playlistService.list(pageBean, params);
		request.setAttribute("title",title);
		return "listSuccess";
	}
	
	/**
	 * 详情
	 * @return
	 */
	public String view(){
		String playlistid = request.getParameter("playlistid");
		if(playlistid!=null && !"".equals(playlistid)){
			Playlist cPlaylist = playlistService.findById(Integer.parseInt(playlistid));
			request.setAttribute("playlist",cPlaylist);
			//歌单下的音乐
			//歌单中的音乐列表
			List<Record> recordList = recordService.find(Integer.parseInt(playlistid));
			request.setAttribute("recordList", recordList);
		}
		return "viewSuccess";
	}
	
	/**
	 * 跳转到添加或者修改页面
	 * @return
	 */
	public String addOrUpdate(){
		String playlistid = request.getParameter("playlistid");
		if(playlistid!=null && !"".equals(playlistid)){
			Playlist cPlaylist = playlistService.findById(Integer.parseInt(playlistid));
			request.setAttribute("playlist",cPlaylist);
		}
		return "addOrUpdateSuccess";
	}
	
	/**
	 * 添加或者更新，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doAddOrUpdate(){
		if(playlist.getId()!=null){
			playlistService.update(playlist);
		}else{
			playlistService.save(playlist);
		}
		resultMap.put("success",1);
		resultMap.put("url","admin_playlist_list");
		return "doAddOrUpdateSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String playlistid = request.getParameter("playlistid");
		playlistService.delete(Integer.parseInt(playlistid));
		resultMap.put("success",1);
		resultMap.put("url","admin_playlist_list");
		return "deleteSuccess";
	}
	
	public Playlist getModel() {
		return playlist;
	}
	
	public void setPageBean(PageBean<Playlist> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Playlist> getPageBean() {
		return pageBean;
	}

	public PlaylistService getPlaylistService() {
		return playlistService;
	}

	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public MusicService getMusicService() {
		return musicService;
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	
}