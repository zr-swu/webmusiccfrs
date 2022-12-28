package com.wmcfrs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmcfrs.model.User;
import com.wmcfrs.util.CommonUtil;
import com.wmcfrs.util.Constant;

/**
 * 基础控制器（主要便于自控制器获取当前登录用户，获取request，实现公共上传文件功能，返回公共结果集合）
 */
public class BaseController extends ActionSupport implements RequestAware {

	private static final long serialVersionUID = 1L;
	// 获取请求
	public HttpServletRequest request;
	// 封装ajax请求的结果数据
	public Map<Object, Object> resultMap = new HashMap<Object, Object>();
	// 查询参数集合
	public Map<String, Object[]> params = new HashMap<String, Object[]>();
	private File file; // 得到上传的文件
	private String fileContentType;// 上传文件类型
	private String fileFileName;// 上传文件名字

	public void setRequest(Map<String, Object> arg0) {
		request = ServletActionContext.getRequest();
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public User getCurrentUser(){
		return (User) request.getSession().getAttribute(Constant.session_user);
	}
	
	/**
	 * 文件上传
	 * @return
	 */
	public String upload(){
		System.out.println("上传文件："+file);
		System.out.println("上传文件类型："+fileContentType);
		System.out.println("上传文件名称："+fileFileName);
		int success = 0;//上传失败
		String newFileName = "";
        //获取要保存文件夹的物理路径(绝对路径)
        String realPath = ServletActionContext.getServletContext().getRealPath("");
        realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
        realPath = realPath+"\\"+Constant.uploadAppDir+"\\"+Constant.uploadDir;
        File cFile = new File(realPath);
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if(!cFile.exists())
        	cFile.mkdirs();
        try {
        	newFileName = System.currentTimeMillis()+"_"+CommonUtil.getRandom()+
        			fileFileName.substring(fileFileName.lastIndexOf( "." ));
            System.out.println("新文件名称："+newFileName);
        	//保存文件
            FileUtils.copyFile(file, new File(cFile,newFileName));
            success = 1;//上传成功
        } catch (IOException e) {
            e.printStackTrace();
        }
		resultMap.put("newFileName",newFileName);
		resultMap.put("success",success);
		return "uploadSuccess";
	}

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
