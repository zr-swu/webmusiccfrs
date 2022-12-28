package com.wmcfrs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String header;
	private String createtime;
	private Set comments = new HashSet(0);
	private Set collections = new HashSet(0);

	public User() {
	}

	public User(String username, String password, String email, String header,
			String createtime, Set comments, Set collections) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.header = header;
		this.createtime = createtime;
		this.comments = comments;
		this.collections = collections;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getCollections() {
		return this.collections;
	}

	public void setCollections(Set collections) {
		this.collections = collections;
	}

}