<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<div class="header"
	style="height:75px;width:100%;
				position: fixed;z-index: 100;background-color: 
				#C20C0C;border-bottom: 1px solid #a40011;" align="center">
	<a href="${pageContext.request.contextPath}/index"
		style="height:75px;line-height:65px;display:inline-block;" title="在线音乐推荐系统">
		<img style="height:50px;width:50px" alt="在线音乐推荐系统" title="在线音乐推荐系统" 
		src="${pageContext.request.contextPath}/static/img/33.png">
		&nbsp;&nbsp; <font color="white"
		style="font-weight: 400;font-size:25px">在线音乐推荐系统</font> </a>
	<div style="width:40%;display:inline-block" align="center">
		<div class="search_footer"
			style="height:100px;line-height:75px;display:inline-block;width:80%" align="center">
			<form action="${pageContext.request.contextPath}/listMusic" method="post" >
				<input type="text" placeholder="请输入音乐名称" maxlength="20" name="musicname" value="${musicname }" style="color:#333"><input
				type="submit" value=" 搜 索 " style="background: #ff410f;position: relative;" title="搜索">
			</form>
		</div>
	</div>
	<div class="btn-group mb-2" style="width:15%;display:inline-block;color: #fff;" align="center">
		<s:if test="#session.session_user != null">
			<a href="#" style="height:75px;line-height:65px;box-shadow:none;-webkit-box-shadow:none"
				class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 
				<img style="height:35px;width:35px" alt="" 
				src="<%=basePath2%><s:property value="#session.session_user.header"/>" 
				onerror="javascript:this.src='${pageContext.request.contextPath}/static/img/default.jpg';"
				title="<s:property value="#session.session_user.username"/>" >
				&nbsp;&nbsp; <font color="white" style="font-weight: 400;font-size:18px">${session_user.username }</font>
			</a>
		</s:if>
		<s:else>
			<a href="${pageContext.request.contextPath}/login" 
				style="height:75px;line-height:65px;box-shadow:none;
				-webkit-box-shadow:none" title="登录">
				<font color="white" style="font-weight: 400;font-size:18px">登录</font>
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp; / &nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/register" 
				style="height:75px;line-height:65px;box-shadow:none;
				-webkit-box-shadow:none" title="注册">
				<font color="white" style="font-weight: 400;font-size:18px">注册</font>
			</a>
		</s:else>
		<div class="dropdown-menu" style="left:15%">
			<a class="dropdown-item" href="${pageContext.request.contextPath}/viewUser">个人中心</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/listScore">我的评分</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/listCollection">我的收藏</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/commentList">我的评论</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/quit">注销</a>
		</div>
	</div>
</div>