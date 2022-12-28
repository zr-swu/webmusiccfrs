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
<title>用户密码修改【在线音乐推荐系统】</title>
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
									用户密码修改
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
								<form action="${pageContext.request.contextPath}/updatePassword" method="post" class="form-horizontal" id="form" enctype="multipart/form-data">
	          						<input type="hidden" name="id" value="${user.id}"/>
									<div class="form-group">
						                <label for="inputEmail" class="col-sm-2 col-md-2 control-label" style="width:25%">原密码：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input type="password" name="oldPassword" id="oldPassword" class="form-control"  maxlength="20" />
						                </div>
						            </div><div class="clearfix"> </div>
						            <br>
						            <div class="form-group">
						                <label for="inputEmail" class="col-sm-2 col-md-2 control-label" style="width:25%">新密码：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input type="password" class="form-control" name="password" id="password" maxlength="20" />
						                </div>
						            </div>
						            <div class="clearfix"> </div>
						            <br>
						            <div class="form-group">
						                <label for="inputEmail" class="col-sm-2 col-md-2 control-label" style="width:25%">确认密码：</label>
						                <div class="col-sm-6 col-md-6">
						                    <input type="password" class="form-control" name="confPassword" id="confPassword" maxlength="20" />
						                </div>
						            </div>
						            <div class="clearfix"> </div>
						            <br>
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
</body>
<script>
	$(function(){
		$("#submitBtn").on("click",function(){
				var temp = true;
				$("#form").find("input[type='password']").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if(!temp){
					layer.msg("数据不能为空!");return false;
				}
				if($("#password").val()!=$("#confPassword").val()){
					layer.msg("两次密码不一致!");return false;
				}
				var options = {
		                url: $("#form").attr("action"),
		                type: 'post',
		                dataType: 'json',
		                data: $("#form").serialize(),
		                success: function (data) {
	                    	if(data.success>0){
	                    		layer.msg("操作成功！请重新登录！");
	                    		setTimeout(function () {location.href="${pageContext.request.contextPath}/"+data.url; }, 1000);
	                    	}else{
	                    		if(data.success==-1){
	                    			layer.msg("原密码不正确！");return false;
	                    		}
	                    		layer.msg("操作失败！");
	                    	}
		                 }
			         };
	                $.ajax(options);
		});
	});
</script>
</html>