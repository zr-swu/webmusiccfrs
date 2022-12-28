package com.wmcfrs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 音乐实体类
 */
public class Music implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String musicname;
	private String url;
	private Set collections = new HashSet(0);
	private Set records = new HashSet(0);

	public Music() {
	}

	public Music(String musicname, String url, Set collections, Set records) {
		this.musicname = musicname;
		this.url = url;
		this.collections = collections;
		this.records = records;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMusicname() {
		return this.musicname;
	}

	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getCollections() {
		return this.collections;
	}

	public void setCollections(Set collections) {
		this.collections = collections;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

}