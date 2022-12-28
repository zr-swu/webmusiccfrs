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
               <strong>歌单详情</strong>
           </div>
           <div class="card-body card-block">
               <form action="" method="post">
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">歌单名称：</label></div>
                       <div class="col-12 col-md-6">
                       	<input type="text" id="title" name="title" value="${playlist.title}" readonly="readonly"
                       		maxlength="100" class="form-control">
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">歌单封面：</label></div>
                       <div class="col-12 col-md-6">
		                    <img style="width:100px;height:100px" src="<%=basePath2%>/${playlist.image}" />
                       </div>
                   </div>
                   <div class="row form-group">
                       <div class="col col-md-2"><label for="text-input" class=" form-control-label">简介：</label></div>
                       <div class="col-12 col-md-6">
                       		<textarea id="content" name="content" maxlength="2000"  readonly="readonly" 
                       			class="form-control">${playlist.content}</textarea>
                       </div>
                   </div>
               </form>
               
               
               
               
               
             <form action="" method="post" id="form">
		      	<div class="card">
		          <div class="card-header" style="padding: 0.53rem 1.25rem;">
		              <strong class="card-title">歌单中的音乐</strong>
		              <div style="display:inline-block;padding-left:70px">	</div>
		          </div>
		          <div class="card-body">
		              <table class="table table-bordered" style="text-align: center;">
		                  <thead>
		                      <tr>
		                          <th scope="col">序号</th>
		                          <th scope="col">音乐名称</th>
		                      </tr>
		                  </thead>
		                  <tbody>
		                  	<s:if test="#request.recordList!=null && #request.recordList.size>0">
		                  		<s:iterator var="record" value="#request.recordList" status="status">
		                  			<tr>
			                      		<td><s:property value="#status.index+1"/></td>
			                      		<td><s:property value="#record.music.musicname"/></td>
			                      	</tr>
		                  		</s:iterator>
		                    </s:if>
		                  </tbody>
		              </table>
		          </div>
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