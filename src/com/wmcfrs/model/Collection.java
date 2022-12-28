package com.wmcfrs.model;

/**
 * 收藏实体类
 */
public class Collection implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Music music;
	private User user;

	public Collection() {
	}

	public Collection(Music music, User user) {
		this.music = music;
		this.user = user;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}