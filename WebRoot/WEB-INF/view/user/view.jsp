<%@page import="com.wmcfrs.util.Constant"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String uploadAppDir = (String)request.getSession().getServletContext().getAttribute("uploadAppDir");
String uploadDir = (String)request.getSession().getServletContext().getAttribute("uploadDir");
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
	+"/"+uploadAppDir+"/"+uploadDir+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>用户详情【在线音乐推荐系统】</title>
<%@ include file="../common/css.jsp"%>
<style>
	.header a {
		text-decoration: none;
	}
	.header a:hover {
		color: #ff410f;
	}
	.dropdown-menu a {
		display: block;
		height: 30px;
		margin-left: 10px
	}
	.content-grid img:hover {
		opacity: 0.8;
	}
	.like {
		font-size: 20px;
		color: #ccc;
		cursor: pointer;
	}
	.cleanfloat::after {
		display: block;
		content: "";
		visibility: hidden;
		height: 0;
	}
	.score ul li {
		list-style: none;
		display: inline-block;
		color: #ccc;
		cursor: pointer;
		font-size: 20px;
	}
</style>
</head>
<body class="sticky-header left-side-collapsed">
	<section>
		<div class="main-content" style="margin-left:0px;padding-top:0px">
			<%@ include file="../common/header.jsp"%>
			<div id="page-wrapper">
				<div class="inner-content" align="center">
					<div class="music-left" style="float:none;">
						<div class="albums">
							<div class="tittle-head">
								<h3 class="tittle">
									用户详情
								</h3>
								<div class="clearfix"></div>
							</div>
							<div class="col-md-3 content-grid">
								<a class="play-icon popup-with-zoom-anim" href="javascript:void(null)"><img
									 src="<%=basePath2%><s:property value="#request.user.header"/>" onerror="javascript:this.src='${pageContext.request.contextPath}/static/img/default.jpg';"
									 title="<s:property value="#request.user.username"/>">
								</a>
							</div>
							<div class="col-md-3" style="width:70%;text-align: left">
								<div style="margin-bottom:20px;font-size: 24px;font-weight: 500;color:black"><s:property value="#request.user.username"/></div>
								<div style="margin-bottom:20px;font-size: 16px;color: #165aa6;">邮箱：<s:property value="#request.user.email"/></div>
								<div style="margin-bottom:20px;font-size: 16px;color: #165aa6;">注册时间：<s:property value="#request.user.createtime"/></div>
							</div>
						</div>
						<div class="clearfix"> </div>
						<br><br>
						<div style="display:inline-block;">
							<a href="${pageContext.request.contextPath}/editUser" class="toggle-btn"  style="width:100px;background:#ff410f">修改信息</a>
						</div>
						<div style="display:inline-block;">
							<a href="${pageContext.request.contextPath}/editPassword" class="toggle-btn"  style="width:100px;background:#45B39D">修改密码</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</section>
	<%@ include file="../common/js.jsp"%>
</body>
<script>
</script>
</html>