package com.wmcfrs.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;
import com.wmcfrs.util.Constant;

/**
 * applicaiton监听器
 * 初始化applicaiton数据，用于页面获取一些静态值
 */
public class InitAppListener implements ServletContextAware{

	//音乐service
	private MusicService musicService;
	//歌单service
	private PlaylistService playlistService;
	//歌单音乐service
	private RecordService recordService;
	
	public void setServletContext(ServletContext arg0) {
		//上传文件项目名称
		arg0.setAttribute("uploadAppDir",Constant.uploadAppDir);
		arg0.setAttribute("uploadDir",Constant.uploadDir);
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

}
