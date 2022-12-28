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
	<title>我的收藏【在线音乐推荐系统】</title>
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
								<h3 class="tittle"><span class="new">LOVE</span> 我的收藏</h3>
								<div class="clearfix"> </div>
							</div>
							<div class="bs-docs-example" style="color:black;">
								<table class="table table-bordered" align="center">
									<thead>
										<tr>
											<th>序号</th>
										  	<th>音乐名称</th>
										  	<th>收藏</th>
										  	<th>播放</th>
										</tr>
								    </thead>
								    <tbody>
								      	<s:if test="#request.pageBean!=null && #request.pageBean.list.size()>0">
		                  			      	<s:iterator var="collection" value="#request.pageBean.list" status="status">
										      	<tr>
											      	<td><s:property value="#status.index+1"/></td>
											  	  	<td style="width:50%"><s:property value="#collection.music.musicname"/></td>
											      	<td>
								  				      	<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击取消收藏" title="点击取消收藏" 
								  							data-collectionid="<s:property value="#collection.id"/>">已收藏</a>
											      	</td>
											  	  	<td><a href="<s:property value="#collection.music.url"/>" target="_blank" >播放</a></td>
											  	</tr>
										  	</s:iterator>
									  	</s:if>
									  	<s:else>
									      	<tr><td colspan="4"><font style="color:black">暂无数据</font></td><tr>
									  	</s:else>
								  	</tbody>
							  	</table>
						  	</div>
						</div>
						<div class="clearfix"> </div>
						<form action="${pageContext.request.contextPath}/listCollection" method="post" id="form">
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
  <script type="text/javascript">
  		//收藏
  		$(".likeMusic").click(function() {
			if(!loginTemp){
				return false;
			}
			var musicid = $(this).attr("data-musicid");
			musicid = musicid==undefined?"":musicid;
			var collectionid = $(this).attr("data-collectionid");
			collectionid = collectionid==undefined?"":collectionid;
			var data = "url=listCollection&musicid="+musicid+"&collectionid="+collectionid;
			common_ajax("addCollection",data);			
		});
  </script>
</html>