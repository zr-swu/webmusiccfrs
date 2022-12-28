package com.wmcfrs.model;

/**
 * 歌单歌曲实体类
 */
public class Record implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Music music;
	private Playlist playlist;

	public Record() {
	}

	public Record(Music music, Playlist playlist) {
		this.music = music;
		this.playlist = playlist;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Music getMusic() {
		return this.music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public Playlist getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

}