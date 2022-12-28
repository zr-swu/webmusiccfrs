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
<title>用户信息维护【在线音乐推荐系统】</title>
<%@ include file="../common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/webuploader-0.1.5/webuploader.css">
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
									用户信息维护
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
								<form action="${pageContext.request.contextPath}/updateUser" method="post" class="form-horizontal" id="form" enctype="multipart/form-data">
	          						<input type="hidden" name="id" value="${user.id}"/>
									<div class="form-group">
						                <label for="inputEmail" class="col-sm-2 col-md-2 control-label" style="width:25%">用户名：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input type="text" name="username" class="form-control" value="${user.username }"  maxlength="20" readonly="readonly"/>
						                </div>
						            </div><div class="clearfix"> </div>
						            <br>
						            <div class="form-group">
						                <label for="inputEmail" class="col-sm-2 col-md-2 control-label" style="width:25%">邮箱：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input type="text" class="form-control" name="email" maxlength="20" value="${user.email }"/>
						                </div>
						            </div>
						            <div class="clearfix"> </div>
						            <br>
									<div class="form-group">
						                <label for="address" class="col-sm-2 col-md-2 control-label" style="width:25%">头像：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input name="header" type="hidden" id="header" value="${user.header}"/>
						                    <div id="uploader_header">
											    <!--用来存放文件信息-->
											    <div id="thelist_header" class="uploader-list"></div>
											    <div class="btns">
											        <div id="picker_header">选择文件</div>
											        <button id="ctlBtn_header" class="btn btn-default" type="button">开始上传</button>
											    </div>
											</div>
						                </div>
						            </div>
								</form>
							</div>
						</div>
						<div class="clearfix"> </div>
						<br><br>
						<div style="display:inline-block;">
							<a href="javascript:void(null)" id="submitBtn" class="toggle-btn"  style="width:100px;background:#ff410f">提交信息</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</section>
	<%@ include file="../common/js.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/webuploader-0.1.5/webuploader.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/uploader.js"></script>
</body>
<script>
	var basePath2 = "<%=basePath2%>"
	$(function(){
		//图片
		uploadFile("thelist_header","ctlBtn_header","picker_header","header");
		
		$("#submitBtn").on("click",function(){
				var temp = true;
				$("#form").find("input[type='text']").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if(!temp){
					layer.msg("数据不能为空!");return false;
				}
				if($("#header").val()==null || $("#header").val()==""){
					layer.msg("请上传图片!");return false;
				}
				common_ajax($("#form").attr("action"),$("#form").serialize());
				
		});
	});
</script>
</html>