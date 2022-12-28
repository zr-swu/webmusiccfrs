package com.wmcfrs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 歌单实体类
 */
public class Playlist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String content;
	private String image;
	private Set comments = new HashSet(0);
	private Set records = new HashSet(0);

	public Playlist() {
	}

	public Playlist(String title, String content, String image, Set comments,
			Set records) {
		this.title = title;
		this.content = content;
		this.image = image;
		this.comments = comments;
		this.records = records;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

}