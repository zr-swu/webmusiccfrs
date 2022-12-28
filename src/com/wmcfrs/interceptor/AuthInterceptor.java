package com.wmcfrs.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wmcfrs.util.Constant;

/**
 * 权限拦截器，登录拦截（有部分操作是需要用户或者管理员登录后才能有权限操作）
 */
public class AuthInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();
		System.out.println("权限验证："+actionName);
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		//管理员权限设置开始
		String adminUri = "admin_index_login,admin_index_doLogin,admin_index_quit,";
		if(actionName.contains("admin_") || actionName.equals("admin")){
			if(!adminUri.contains(actionName+",")){
				if(session.getAttribute(Constant.session_admin)==null){
					return "adminLogin";
				}
			}
		}
		//管理员权限设置结束
		
		//用户权限设置开始
		String userUri = "index,musicView,listMusic,playlistView,playlistList," +
				"login,doLogin,register,doRegister,quit,upload,";
		if(!actionName.contains("admin_") && !actionName.equals("admin")){
			if(!userUri.contains(actionName+",")){
				if(session.getAttribute(Constant.session_user)==null){
					return "login";
				}
			}
		}
		//用户权限设置结束
		
		return invocation.invoke();
	}

}
