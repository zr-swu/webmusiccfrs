package com.wmcfrs.controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wmcfrs.model.Music;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.util.PageBean;

/**
 * 管理员音乐控制器
 */
public class AdminMusicController extends BaseController 
	implements ModelDriven<Music>{

	private static final long serialVersionUID = 1L;
	//实体
	private Music music = new Music();
	//音乐service
	private MusicService musicService;
	//分页
	private PageBean<Music> pageBean = new PageBean<Music>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		String musicname = request.getParameter("musicname");
		params.put("musicname",new Object[]{"like",musicname});
		pageBean = musicService.list(pageBean, params);
		request.setAttribute("musicname",musicname);
		return "listSuccess";
	}
	
	/**
	 * 跳转到添加或者修改页面
	 * @return
	 */
	public String addOrUpdate(){
		String musicid = request.getParameter("musicid");
		if(musicid!=null && !"".equals(musicid)){
			Music cMusic = musicService.findById(Integer.parseInt(musicid));
			request.setAttribute("music",cMusic);
		}
		return "addOrUpdateSuccess";
	}
	
	/**
	 * 添加或者更新，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String doAddOrUpdate(){
		if(music.getId()!=null){
			musicService.update(music);
		}else{
			musicService.save(music);
		}
		resultMap.put("success",1);
		resultMap.put("url","admin_music_list");
		return "doAddOrUpdateSuccess";
	}
	
	/**
	 * 删除，ajax异步请求，返回json格式数据
	 * @return
	 */
	public String delete(){
		String musicid = request.getParameter("musicid");
		musicService.delete(Integer.parseInt(musicid));
		resultMap.put("success",1);
		resultMap.put("url","admin_music_list");
		return "deleteSuccess";
	}
	
	public Music getModel() {
		return music;
	}
	
	public void setPageBean(PageBean<Music> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Music> getPageBean() {
		return pageBean;
	}

	public MusicService getMusicService() {
		return musicService;
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}
	
}