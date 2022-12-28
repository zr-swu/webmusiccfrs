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
	<title>我的评论【在线音乐推荐系统】</title>
	<%@ include file="../common/css.jsp" %>
	<style>
		.header a{
			text-decoration:none;
		}
		.header a:hover{
			color:#ff410f;
		}
		.dropdown-menu a{
			display:block;
			height:30px;
			margin-left:10px
		}
		.content-grid img:hover {
		    opacity: 0.8;
		}
		.layui-layer-rim{
			text-align:center;
			line-height:180px;
			font-size:14px
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
			<%@ include file="../common/header.jsp" %>
			<div id="page-wrapper">
				<div class="inner-content" align="center">
					<div class="music-left" style="float:none;">
						<div class="albums">
							<div class="tittle-head">
								<h3 class="tittle"><span class="new">COMMENT</span> 我的评论</h3>
								<div class="clearfix"> </div>
							</div>
							<div class="bs-docs-example" style="color:black;">
								<table class="table table-bordered" align="center">
								  	<thead>
										<tr>
									  		<th>序号</th>
									  		<th>歌单名称</th>
									  		<th>评论内容</th>
									  		<th>评论时间</th>
									  		<th>操作</th>
										</tr>
								  	</thead>
								  	<tbody>
									  	<s:if test="#request.pageBean!=null && #request.pageBean.list.size()>0">
			                  				<s:iterator var="comment" value="#request.pageBean.list" status="status">
												<tr>
													<td style="width:5%"><s:property value="#status.index+1"/></td>
												  	<td style="width:30%">
												  		<a href="${pageContext.request.contextPath}/playlistView?playlistid=<s:property value="#comment.playlist.id"/>">
												  			<s:property value="#comment.playlist.title"/>
												  		</a>
												  	</td>
												  	<td style="width:40%">
												  		<div style="word-break:break-all;word-wrap:break-word;width:100%">
												  			<s:property value="#comment.comment"/>
												  		</div>
												  	</td>
												  	<td style="width:15%"><s:property value="#comment.createtime"/></td>
												  	<td style="width:10%">
												  		<a href="javascript:doDelete('/deleteComment?commentid=<s:property value="#comment.id"/>')">删除</a>
												  	</td>
												</tr>
											</s:iterator>
										</s:if>
										<s:else>
											<tr><td colspan="5"><font style="color:black">暂无数据</font></td><tr>
										</s:else>
									</tbody>
								</table>
						  	</div>
						</div>
						<div class="clearfix"> </div>
						<form action="${pageContext.request.contextPath}/commentList" method="post" id="form">
    		  				<input type="hidden" name="pageBean.page" value="${pageBean.page}" id="page">
    		  			</form>
						<%@include file="../common/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
     	<%@ include file="../common/footer.jsp" %>
	</section>
	<%@ include file="../common/js.jsp" %>
  </body>
</html>
<script>
  	var basePath = "${pageContext.request.contextPath}/";
	function doDelete(url){
		//询问框
		layer.confirm('确认删除？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
		  common_ajax_other(url);
		}, function(){
		  
		});
	}  	
</script>