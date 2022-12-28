<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js"></script>
<style>
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
<div class="albums">
	<div class="tittle-head">
		<h3 class="tittle"><span class="new">CFMusic</span> 个性化推荐音乐</h3>
		<div class="clearfix"> </div>
	</div>
	
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
	<s:if test="#request.cfMusicList!=null && #request.cfMusicList.size()>0">
    	<s:iterator var="music" value="#request.cfMusicList" status="status">
        	<tr>
			  <td><s:property value="#status.index+1"/></td>
			  <td style="width:50%"><s:property value="#music.musicname"/></td>
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
	<tr><td colspan="5"><font style="color:black">暂无数据</font></td><tr>
	</s:else>
	</tbody>
	</table>
	
</div><div class="clearfix"> </div>
<script>
	$(function(){
		
/* 		var scoreTemp = parseInt("${score.point ==null?0:score.point}");
		var countTemp = 0;
		$(".cleanfloat").find("li").each(function(){
			if(countTemp<scoreTemp){
				$(this).css("color", '#f00');
			countTemp++;
			}
		}); */
		
		var scoreListTemp = eval('${scoreList}');
// 		console.info(scoreListTemp);
		if(scoreListTemp!=null && scoreListTemp!="" && scoreListTemp.length>0){
			$(".cleanfloat").each(function(){
				for(var i=0;i<scoreListTemp.length;i++){
					var musicidTemp = scoreListTemp[i].music.id;
					var musicid = $(this).attr("data-info");
					if(musicid==musicidTemp){
						var point = scoreListTemp[i].point;
// 						console.info(point);
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
			var data = "url=index&musicid="+$(this).parent().attr("data-info")+"&point="+$(this).attr("data-info");
			common_ajax("addScore",data);
		});
		
		
		$(".likeMusic").click(function() {
			if(!loginTemp){
				return false;
			}
			var musicid = $(this).attr("data-musicid");
			musicid = musicid==undefined?"":musicid;
			var collectionid = $(this).attr("data-collectionid");
			collectionid = collectionid==undefined?"":collectionid;
			var data = "url=index&playlistid=${playlist.id}&musicid="+musicid+"&collectionid="+collectionid;
// 			alert(data);
			common_ajax("addCollection",data);			
		});
		
	});

</script>