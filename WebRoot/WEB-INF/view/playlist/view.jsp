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
<title>歌单详情【在线音乐推荐系统】</title>
<%@ include file="../common/css.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style2.css">
<script src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js"></script>
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
			<%@ include file="../common/header.jsp"%>
			<div id="page-wrapper">
				<div class="inner-content" align="center">
					<div class="music-left" style="float:none;">
						<div class="albums">
							<div class="tittle-head">
								<h3 class="tittle">
									歌单详情
								</h3>
								<div class="clearfix"></div>
							</div>
							<div class="col-md-3 content-grid">
								<a class="play-icon popup-with-zoom-anim" href=""><img
									src="<%=basePath2%><s:property value="#request.playlist.image"/>" title="<s:property value="#request.playlist.title"/>">
								</a>
							</div>
							<div class="col-md-3" style="width:70%;text-align: left">
								<div style="margin-bottom:20px;font-size: 24px;font-weight: 500;color:black"><s:property value="#request.playlist.title"/></div>
								<div style="margin-bottom:20px;font-size: 16px;color: #165aa6;">${playlist.content}</div>
							</div>
						</div>
						<div class="response coment-form" style="float:none;text-align: left;width:100%;clear:both;">
						  	<div class="bs-docs-example" style="color:black;">
								<h4>音乐列表</h4>
								<table class="table table-bordered" align="center">
								  	<thead>
										<tr>
									  		<th>序号</th>
									  		<th>音乐名称</th>
									  		<th>播放</th>
									  		<th>收藏</th>
									  		<th>评分</th>
										</tr>
								  	</thead>
								  	<tbody>
								  		<s:if test="#request.recordList!=null && #request.recordList.size()>0">
		                  					<s:iterator var="record" value="#request.recordList" status="status">
												<tr>
											  		<td><s:property value="#status.index+1"/></td>
											  		<td style="width:50%"><s:property value="#record.music.musicname"/></td>
											  		<td><a href="<s:property value="#record.music.url"/>" target="_blank" >播放</a></td>
											  		<td>
													  	<s:if test="#request.collectionList !=null && #request.collectionList.size()>0">
													  		<s:set var="countTemp" value="0"/>
													  		<s:iterator var="collection" value="#request.collectionList">
													  			<s:if test="#collection.music.id == #record.music.id">
													  				<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击取消收藏" title="点击取消收藏" 
													  					data-collectionid="<s:property value="#collection.id"/>">已收藏</a>
													  			</s:if>
													  			<s:else>
													  				<s:set var="countTemp" value="#countTemp+1"/>
													  			</s:else>
													  		</s:iterator>
													  		<s:if test="#countTemp == #request.collectionList.size()">
													  			<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击收藏" title="点击收藏" 
													  				data-musicid="<s:property value="#record.music.id"/>">收藏</a>
													  		</s:if>
													  	</s:if>
													  	<s:else>
													  		<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击收藏" title="点击收藏" 
													  			data-musicid="<s:property value="#record.music.id"/>">收藏</a>
													  	</s:else>
											  		</td>
											  		<td>
									  					<div class="score" style="display: inline-block;margin-right:80px" >
															<ul class="cleanfloat" style="display: inline-block" data-info="<s:property value="#record.music.id"/>">
																<li title="1分" data-info="1" class="do_before_login_other">&#9733;</li><li title="2分" data-info="2" class="do_before_login_other">&#9733;</li><li title="3分" data-info="3" class="do_before_login_other">&#9733;</li><li title="4分" data-info="4" class="do_before_login_other">&#9733;</li><li title="5分" data-info="5" class="do_before_login_other">&#9733;</li>
															</ul>
														</div>
											  		</td>
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
						<div class="coment-form" style="float:none;text-align: left;width:100%;clear:both;vertical-align: middle;">
							<h4>歌单评论</h4>
							<textarea maxlength="200" style="display:inline-block" name="comment" id="comment"></textarea>
							<div style="display:inline-block;">
								<a class="toggle-btn do_before_login_other" id="a_comment" style="width:100px;background:#45B39D">发表评论</a>
							</div>
						</div>
						<div class="response coment-form" style="float:none;text-align: left;width:100%;clear:both;">
							<h4>精彩评论</h4>
							<div class="media response-info">
								<s:if test="pageBeanEx!=null && pageBeanEx.list.size()>0">
	                  				<s:iterator var="comment" value="pageBeanEx.list" status="status">
	                  					<div class="media-left response-text-left">
											<a class="" href="javascript:void(null)"><img class="media-object" style="width:68px;height:68px"
												 src="<%=basePath2%><s:property value="#comment.user.header"/>" onerror="javascript:this.src='${pageContext.request.contextPath}/static/img/default.jpg';"
												 title="<s:property value="#comment.user.username"/>">
											</a>
											<h5>
												<a href="javascript:void(null)"><s:property value="#comment.user.username"/></a>
											</h5>
										</div>
										<div class="media-body response-text-right">
											<p><s:property value="#comment.comment"/></p>
											<ul>
												<li><s:property value="#comment.createtime"/></li>
											</ul>
										</div>
										<div class="clearfix"></div>
									</s:iterator>
								</s:if>
								<s:else>
									<font style="color:black">暂无数据</font>
								</s:else>
							</div>
							<form action="${pageContext.request.contextPath}/playlistView" method="post" id="form">
	    		  				<input type="hidden" name="pageBeanEx.page" value="${pageBeanEx.page}" id="page">
	    		  				<input type="hidden" name="playlistid" value="${playlist.id}" >
	    		  			</form>
							<%@include file="../common/pageex.jsp" %>
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
		$(function() {
			//评分
			var scoreListTemp = eval('${scoreList}');
			if(scoreListTemp!=null && scoreListTemp!="" && scoreListTemp.length>0){
				$(".cleanfloat").each(function(){
					for(var i=0;i<scoreListTemp.length;i++){
						var musicidTemp = scoreListTemp[i].music.id;
						var musicid = $(this).attr("data-info");
						if(musicid==musicidTemp){
							var point = scoreListTemp[i].point;
							for(var j=0;j<point;j++){
								$(this).find("li").eq(j).css("color", '#f00');
							}
						}
					}
				});
			}
			
			$(".cleanfloat").find("li").click(function() {
				if(!loginTemp){
					return false;
				}
				$(this).css("color", '#f00');
				$(this).prevAll().css("color", '#f00');
				$(this).nextAll().css("color", '#ccc');
				var data = "musicid="+$(this).parent().attr("data-info")+"&point="+$(this).attr("data-info");
				common_ajax("addScore",data);
			});
			
			//收藏
			$(".likeMusic").click(function() {
				if(!loginTemp){
					return false;
				}
				var musicid = $(this).attr("data-musicid");
				musicid = musicid==undefined?"":musicid;
				var collectionid = $(this).attr("data-collectionid");
				collectionid = collectionid==undefined?"":collectionid;
				var data = "playlistid=${playlist.id}&musicid="+musicid+"&collectionid="+collectionid;
				common_ajax("addCollection",data);			
			});
			
			//评论
			$("#a_comment").click(function(){
				if(!loginTemp){
					return false;
				}
				var comment = $("#comment").val();
				if(comment==null || comment==""){
					layer.msg("评论内容不能为空！");
					return false;
				}
				var data = "playlistid=${playlist.id}"+"&comment="+window.encodeURI(window.encodeURI(comment));
				common_ajax("addComment",data);
			});
			
		});
	</script>
</html>