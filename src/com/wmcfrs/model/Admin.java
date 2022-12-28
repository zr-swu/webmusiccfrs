package com.wmcfrs.model;

/**
 * 管理员实体类
 */
public class Admin implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String adminname;
	private String password;

	public Admin() {
	}

	public Admin(String adminname, String password) {
		this.adminname = adminname;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminname() {
		return this.adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}