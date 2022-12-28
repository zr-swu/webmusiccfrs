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
	<title>首页【在线音乐推荐系统】</title>
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
	</style>
 </head> 
 <body class="sticky-header left-side-collapsed">
    <section>
		<div class="main-content" style="margin-left:0px;padding-top:0px">
			<%@ include file="../common/header.jsp" %>
			<div id="page-wrapper">
				<div class="inner-content" align="center">
					<div class="music-left" style="float:none;">
						<div class="albums" >
							<div class="tittle-head">
								<h3 class="tittle"><span class="new">ALL</span> 全部歌单</h3>
								<a href="${pageContext.request.contextPath}/playlistList">
									<h4 class="" style="float:right;font-weight:600">查看更多</h4>
								</a>
								<div class="clearfix"> </div>
							</div>
							<s:if test="pageBean.list!=null && pageBean.list.size()>0">
	                  			<s:iterator var="playlist" value="pageBean.list" status="status">
	                  				<s:if test="(#status.index+1)%4==0">
	                  					<div class="col-md-3 content-grid last-grid">
											<a class="play-icon popup-with-zoom-anim" 
												href="${pageContext.request.contextPath}/playlistView?playlistid=<s:property value="#playlist.id"/>">
													<img src="<%=basePath2%><s:property value="#playlist.image"/>" title="<s:property value="#playlist.title"/>"></a>
											<a class="button play-icon popup-with-zoom-anim" href="${pageContext.request.contextPath}/playlistView?playlistid=<s:property value="#playlist.id"/>">
												<s:property value="#playlist.title"/></a>
										</div>
										<div class="clearfix"> </div>
	                  				</s:if>
	                  				<s:else>
	                  					<div class="col-md-3 content-grid">
											<a class="play-icon popup-with-zoom-anim" 
												href="${pageContext.request.contextPath}/playlistView?playlistid=<s:property value="#playlist.id"/>">
													<img src="<%=basePath2%><s:property value="#playlist.image"/>" title="<s:property value="#playlist.title"/>"></a>
											<a class="button play-icon popup-with-zoom-anim" href="${pageContext.request.contextPath}/playlistView?playlistid=<s:property value="#playlist.id"/>">
												<s:property value="#playlist.title"/></a>
										</div>
	                  				</s:else>
	                  			</s:iterator>
							</s:if>
							<s:else>
								暂无数据
							</s:else>
						</div>
						<div class="clearfix"> </div>
						<s:if test="#session.session_user != null">
							<%@ include file="cfMusic.jsp" %>
						</s:if>
						<s:else>
							<%@ include file="hot.jsp" %>
						</s:else>
					</div>
				</div>
			</div>
		</div>
     	<%@ include file="../common/footer.jsp" %>
	 </section>
	 <%@ include file="../common/js.jsp" %>
  </body>
</html>