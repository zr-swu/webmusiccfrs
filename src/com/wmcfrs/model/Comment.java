package com.wmcfrs.model;

/**
 * 评论实体类
 */
public class Comment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Playlist playlist;
	private User user;
	private String comment;
	private String createtime;

	public Comment() {
	}

	public Comment(Playlist playlist, User user, String comment,
			String createtime) {
		this.playlist = playlist;
		this.user = user;
		this.comment = comment;
		this.createtime = createtime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Playlist getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}