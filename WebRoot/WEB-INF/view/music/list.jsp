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
	<title>全部音乐【在线音乐推荐系统】</title>
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
								<h3 class="tittle"><span class="new">ALL</span> 全部音乐</h3>
								<div class="clearfix"> </div>
							</div>
							<div class="bs-docs-example" style="color:black;">
								<table class="table table-bordered" align="center">
								  	<thead>
										<tr>
									  		<th>序号</th>
									  		<th style="width:50%">音乐名称</th>
									  		<th>播放</th>
									  		<th>收藏</th>
									  		<th>评分</th>
										</tr>
								  	</thead>
								  	<tbody>
								  		<s:if test="#request.pageBean!=null && #request.pageBean.list.size()>0">
		                  					<s:iterator var="music" value="#request.pageBean.list" status="status">
												<tr>
											  		<td><s:property value="#status.index+1"/></td>
											  		<td><s:property value="#music.musicname"/></td>
											  		<td><a href="<s:property value="#music.url"/>" target="_blank" >播放</a></td>
											  		<td>
													  	<s:if test="#request.collectionList !=null && #request.collectionList.size()>0">
													  		<s:set var="countTemp" value="0"/>
													  		<s:iterator var="collection" value="#request.collectionList">
													  			<s:if test="#collection.music.id == #music.id">
													  				<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击取消收藏" title="点击取消收藏" 
													  					data-collectionid="<s:property value="#collection.id"/>">已收藏</a>
													  			</s:if>
													  			<s:else>
													  				<s:set var="countTemp" value="#countTemp+1"/>
													  			</s:else>
													  		</s:iterator>
													  		<s:if test="#countTemp == #request.collectionList.size()">
													  			<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击收藏" title="点击收藏" 
													  				data-musicid="<s:property value="#music.id"/>">收藏</a>
													  		</s:if>
													  	</s:if>
													  	<s:else>
													  		<a href="javascript:void(null)" class="likeMusic do_before_login_other" alt="点击收藏" title="点击收藏" 
													  			data-musicid="<s:property value="#music.id"/>">收藏</a>
													  	</s:else>
												  	</td>
												  	<td>
									  					<div class="score" style="display: inline-block;margin-right:80px" >
															<ul class="cleanfloat" style="display: inline-block" data-info="<s:property value="#music.id"/>">
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
						<div class="clearfix"> </div>
						<form action="${pageContext.request.contextPath}/listMusic" method="post" id="form">
    		  				<input type="hidden" name="pageBean.page" value="${pageBean.page}" id="page">
    		  				<input type="hidden" name="musicname" value="${musicname}" >
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
  		//收藏音乐
  		$(".likeMusic").click(function() {
			if(!loginTemp){
				return false;
			}
			var musicid = $(this).attr("data-musicid");
			musicid = musicid==undefined?"":musicid;
			var collectionid = $(this).attr("data-collectionid");
			collectionid = collectionid==undefined?"":collectionid;
			var data = "url=listMusic&musicid="+musicid+"&collectionid="+collectionid;
			common_ajax("addCollection",data);			
		});
  	
  		function submitForm(){
  			$("#page").val(1);
  			$("#form").submit();
  		}
  </script>
</html>