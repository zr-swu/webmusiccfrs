package com.wmcfrs.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.wmcfrs.model.Music;
import com.wmcfrs.model.Playlist;
import com.wmcfrs.model.Record;
import com.wmcfrs.service.MusicService;
import com.wmcfrs.service.PlaylistService;
import com.wmcfrs.service.RecordService;

/**
 * 网易云音乐爬虫
 * 爬虫原理：
 * 1、首先分页爬取歌单列表
 * 2、爬取歌单详情中的音乐
 * 每次爬取的数量是maxPage*maxMusic*35
 */
public class DataUtilEx {
	
	//网易云音乐官方网址，首页
	private static String baseUrl = "https://music.163.com/";
	//网易云音乐首页导航栏中的“歌单”页面
	private static String playlistUrl = "discover/playlist";
	
	private static int playlistIdIndex = 1;
	
	private static int musicIdIndex = 1;
	
	private static int recordIdIndex = 1;
	
	private static Map<String,Music> musicMap = new LinkedHashMap<String, Music>();
	
	private static List<Playlist> playlistList = new ArrayList<Playlist>();
	
	private static boolean firstPage = true;//从第一页开始
	
	private static String pageUrl = "";
	
	private static Integer limit = null;
	
	private static Integer offset = null;
	
	private static Integer maxPage = 1;//最大获取的页面数值,通过此数值控制歌单数量，可更改
	
	private static Integer maxMusic = 2;//通过此数值获取每个歌单中最多音乐数量，可更改
	
	private static int rowCount = 1;
	
	private static PlaylistService playlistServiceTemp;
	
	public static void dataUtil(MusicService musicService,
			RecordService recordService,PlaylistService playlistService,String realPath,
			int playlistIdIndexTemp,int musicIdIndexTemp,int recordIdIndexTemp){
		try {
			playlistIdIndex = playlistIdIndexTemp;
			musicIdIndex = musicIdIndexTemp;
			recordIdIndex = recordIdIndexTemp;
			playlistServiceTemp = playlistService;
			page();
			toDatabase(playlistList, musicService, recordService, playlistService,realPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 分页查找歌单（每页35条歌单）
	 * @throws Exception
	 */
	private static void page() throws Exception{
		int temp = 1;//当前第几页临时变量
		while(true){
			//歌单首页
			String toUrl = baseUrl+playlistUrl;
			if(limit!=null){
				toUrl = baseUrl+pageUrl+"limit="+limit+"&offset="+limit*temp;//歌单第几页url
				temp++;
				System.out.println("第"+temp+"页");
			}else{
				System.out.println("第1页");
			}
			System.out.println(toUrl);
			//解析某一页歌单页面
			getPlaylist(toUrl);
			//通过最大页面控制歌单数量
			if(maxPage<=temp){
				break;
			}
		}
	}
	
	/**
	 * 解析某一页歌单页面数据
	 * @param toUrl
	 * @throws Exception
	 */
	private static void getPlaylist(String toUrl) throws Exception{
		//休息一会
		CommonUtil.getRandomSleep();
		URL url = new URL(toUrl);
		//请求页面
		HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
		httpUrlConn.setDoInput(true);  
		httpUrlConn.setRequestMethod("GET"); 
		//获取页面数据
		BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8") );
		StringBuilder html = new StringBuilder();
		String msg = null;
		while(null!= (msg = br.readLine())) {
			html.append(msg);
		}
		br.close();
		//解析页面数据
		Document document = Jsoup.parse(html.toString());
		//判断是否是首页因为链接地址不一样，并获取接下来的几页的页面链接
		if(firstPage){
			Element element11 = document.select("div.u-page").first();
			Element element22 = element11.child(2);
			String href = element22.attr("href");
			pageUrl = href.substring(0,href.lastIndexOf("limit"));
			
			href = href.substring(href.lastIndexOf("limit"));
			href = href.substring(href.indexOf("=")+1,href.lastIndexOf("&"));
			limit = Integer.parseInt(href);
			
			Element element33 = element11.child(element11.childNodeSize()-2);
			href = element33.attr("href");
			href = href.substring(href.lastIndexOf("=")+1);
			offset = Integer.parseInt(href);
					
			firstPage = false;
		}
		
		Element element = document.getElementById("m-pl-container");
		Elements elements = element.getElementsByTag("li");
		//遍历当前歌单页面的所有歌单，并解析进入歌单详情页面
		for(int i=0;i<elements.size();i++){
			Element element2 = elements.get(i);
			Elements elements3 = element2.select(".dec .tit");
			//某个歌单详情页面
			getPlaylistDetail(elements3.first().attr("href"));
			
		}
	}
	
	/**
	 * 解析某个歌单详情页面
	 * @param detailUrl
	 * @throws Exception
	 */
	private static void getPlaylistDetail(String detailUrl) throws Exception{
		//休息一会
		CommonUtil.getRandomSleep();
		URL url = new URL(baseUrl+detailUrl);
		HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
		httpUrlConn.setDoInput(true);
		httpUrlConn.setRequestMethod("GET"); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8") );
		StringBuilder html = new StringBuilder();
		String msg = null;
		while(null!= (msg = br.readLine())) {
			html.append(msg);
		}
		br.close();
		
		Document document = Jsoup.parse(html.toString());
//		write(document.toString());
		Element element = document.getElementById("m-playlist");
		Element element2 = element.select("div.g-wrap6").first();
		
		Element element3 = element2.children().first();
		Element element4 = element3.select("img.j-img").first();
		String image = element4.attr("data-src");//歌单封面
//		System.out.println(image);
		Element element5 = element3.select("h2.f-ff2").first();
		String title = element5.text();//歌单标题
		
		System.out.println(title);
		
		List<Playlist> playlistList2 = playlistServiceTemp.findByTitle(title);
		if(playlistList2!=null && playlistList2.size()>0){
			System.out.println("此歌单已经有记录！不再保存！");
			return;
		}
		
		Element element6 = element3.select("p.intr").first();
		String content = "";//歌单介绍
		if(element6==null){
			content = "没有描述";
		}else{
			content = element6.text();
		}
		
//		System.out.println(content);
		
		Playlist playlist = new Playlist();
		playlist.setId(playlistIdIndex);
		playlistIdIndex++;
		playlist.setTitle(title);
		playlist.setImage(image);
		playlist.setContent(content);
		
		Element element7 = element2.children().get(1);
		Element element8 = element7.select("ul.f-hide").first();
		Elements elements9 = element8.select("li");
		
		Set<Record> sets = new LinkedHashSet<Record>();
		int tempMaxMusic = 0;
		for(Element element10:elements9){
			if(tempMaxMusic>=maxMusic){
				break;
			}
			tempMaxMusic++;
			
			Element element11 = element10.child(0);
			String musicUrl = element11.attr("href");
			musicUrl = baseUrl+musicUrl;
			String musicName = element11.text();
			System.out.println(musicName);
			Music music = null;
			if(musicMap.get(musicUrl)!=null){
				music = musicMap.get(musicUrl);
			}else{
				music = new Music();
				music.setId(musicIdIndex);
				musicIdIndex++;
				music.setMusicname(musicName);
				music.setUrl(musicUrl);
				musicMap.put(musicUrl, music);
			}
			
			Record record = new Record();
			record.setId(recordIdIndex);
			recordIdIndex++;
			record.setMusic(music);
			record.setPlaylist(playlist);
			sets.add(record);
		}
		playlist.setRecords(sets);
		playlistList.add(playlist);
	}
	
	/**
	 * 将数据保存在数据库中
	 * @param playlistList
	 * @param musicService
	 * @param recordService
	 * @param playlistService
	 * @param realPath
	 * @throws Exception
	 */
	private static void toDatabase(List<Playlist> playlistList,MusicService musicService,
			RecordService recordService,PlaylistService playlistService,String realPath) throws Exception{
		Set<String> setTemp =  musicMap.keySet();
		List<Music> list = new ArrayList<Music>();
		for(String key:setTemp){
			Music music = musicMap.get(key);
			list.add(music);
		}
		musicService.save(list);
		
		for(int i=0;i<playlistList.size();i++){
			Playlist playlist = playlistList.get(i);
			String image = playlist.getImage();
			image = getImage(image, realPath);
			playlist.setImage(image);
			playlistService.save(playlist);
			
			Set<Record> records = playlist.getRecords();
			Iterator<Record> iterator = records.iterator();
			List<Record> recordList = Lists.newArrayList(iterator);
			recordService.save(recordList);
		}
	}
	
	/**
	 * 将图片上传到服务器
	 * @param imageUrl 图片的网络地址
	 * @param realPath 图片的保存路径
	 * @return
	 */
	private static String getImage(String imageUrl,String realPath){
		//休息一会
		CommonUtil.getRandomSleep();
		realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
        realPath = realPath+"\\"+Constant.uploadAppDir+"\\"+Constant.uploadDir;
        String newFileName = "";
		File pathFile = new File(realPath);
        if(!pathFile.exists())
        	pathFile.mkdirs();
        URL url = null;
        try {
            url = new URL(imageUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            newFileName = System.currentTimeMillis()+"_"+CommonUtil.getRandom()+".jpg";
            File file = new File(realPath,newFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
 
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return newFileName;
	}
	
}
