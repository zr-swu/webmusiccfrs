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
               <strong>请输入歌单信息</strong>
           </div>
           <div class="card-body card-block">
               <form action="${pageContext.request.contextPath}/admin_playlist_doAddOrUpdate" method="post" id="form">
               	   <input type="hidden" name="id" value="${playlist.id}"/>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">歌单名称：</label></div>
                       <div class="col-12 col-md-6">
                       	<input type="text" id="title" name="title" value="${playlist.title}" maxlength="100" class="form-control">
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">歌单封面：</label></div>
                       <div class="col-12 col-md-6">
                       		<input name="image" type="hidden" id="image" value="${playlist.image}"/>
		                    <div id="uploader_image">
							    <!--用来存放文件信息-->
							    <div id="thelist_image" class="uploader-list"></div>
							    <div class="btns">
							        <div id="picker_image">选择文件</div>
							        <button id="ctlBtn_image" class="btn btn-default" type="button">开始上传</button>
							    </div>
							</div>
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">简介：</label></div>
                       <div class="col-12 col-md-6">
                       		<textarea id="content" name="content" maxlength="2000" class="form-control">${playlist.content}</textarea>
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
	  	uploadFile("thelist_image","ctlBtn_image","picker_image","image");
	  
		$("#submitBtn").on("click",function(){
				var temp = true;
				$("form input[type='text']").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if($("#content").val()==null || $("#content").val()==""){
					temp = false;
				}
				if($("#image").val()==null || $("#image").val()==""){
					temp = false;
				}
				if(!temp){
					layer.msg("数据不能为空!");
				}else{
					common_ajax($("#form").attr("action"),$("#form").serialize() );
				}
		});
	});
  </script>
</html>