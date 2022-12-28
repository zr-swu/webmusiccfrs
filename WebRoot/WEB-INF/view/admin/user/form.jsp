<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String uploadAppDir = (String)request.getSession().getServletContext().getAttribute("uploadAppDir");
String uploadDir = (String)request.getSession().getServletContext().getAttribute("uploadDir");
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
	+"/"+uploadAppDir+"/"+uploadDir+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap3.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/webuploader-0.1.5/webuploader.css">
	<style>
		.serial{
			background:rgba(0,0,0,.03);
		}
	</style>
  </head>
  <body>
    <div class="col-lg-6" style="max-width: 100%;padding:0">
      <div class="card">
           <div class="card-header">
               <strong>请输入用户信息</strong>
           </div>
           <div class="card-body card-block">
               <form action="${pageContext.request.contextPath}/admin_user_doAddOrUpdate" method="post" id="form">
               	   <input type="hidden" name="id" value="${user.id}"/>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">用户名称：</label></div>
                       <div class="col-12 col-md-6">
                       	<input type="text" id="username" name="username" value="${user.username}" maxlength="20"  class="form-control">
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">用户邮箱：</label></div>
                       <div class="col-12 col-md-6">
                       	<input type="text" id="email" name="email" value="${user.email}" maxlength="20"  class="form-control">
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">用户头像：</label></div>
                       <div class="col-12 col-md-6">
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
  					<div class="form-group" align="center">
  						<button class="btn btn-success" id="submitBtn" type="button">&nbsp;&nbsp;提&nbsp;&nbsp;交 &nbsp;&nbsp;</button>
  						&nbsp;&nbsp;&nbsp;&nbsp;
  						<button class="btn btn-info" type="reset">&nbsp;&nbsp;重&nbsp;&nbsp;置&nbsp;&nbsp;</button>
				    </div>
               </form>
           </div>
       </div>
 	</div>
  </body>
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
  <script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/common.js"></script>
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/webuploader-0.1.5/webuploader.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/uploader.js"></script>
  <script>
  	  var basePath = "${pageContext.request.contextPath}/";
  	  var basePath2 = "<%=basePath2%>"
	  $(function(){
	  	uploadFile("thelist_header","ctlBtn_header","picker_header","header");
	  	
		$("#submitBtn").on("click",function(){
				var temp = true;
				$("form input[type='text']").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if($("#header").val()==null || $("#header").val()==""){
					temp = false;
				}
				if(!temp){
					layer.msg("数据不能为空!");
				}else{
					common_ajax_other($("#form").attr("action"),$("#form").serialize() );
				}
		});
	});
  </script>
</html>