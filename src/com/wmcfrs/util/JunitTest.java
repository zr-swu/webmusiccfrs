package com.wmcfrs.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wmcfrs.model.Music;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.Record;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;

/**
 * 单元测试类（运行爬虫）
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"})//加载spring配置文件
public class JunitTest {

	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private RecordService recordService;
	
	//定义文件保存路径，tomcat路径
	private String tomcat_webapp_dir = "F:\\tomcat\\apache-tomcat-7.0.67-x64_myeclipse\\webapps\\";
	
	@Test
	public void test(){
		System.out.println("**********爬取豆瓣网数据开始**********");
		String realPath = tomcat_webapp_dir + Constant.uploadAppDir;
		System.out.println("爬取的数据文件保存在这里："+realPath);
		
		Playlist playlist = playlistService.findLast();
		Music music = musicService.findLast();
		Record record = recordService.findLast();
		int playlistIdIndexTemp = playlist==null?1:(playlist.getId()+1);
		int musicIdIndexTemp = music==null?1:(music.getId()+1);
		int recordIdIndexTemp = record==null?1:(record.getId()+1);
		
		DataUtilEx.dataUtil(musicService, recordService, playlistService, realPath,
				playlistIdIndexTemp, musicIdIndexTemp, recordIdIndexTemp);
		
		System.out.println("**********爬取豆瓣网数据结束**********");
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
