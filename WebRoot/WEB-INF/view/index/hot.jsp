<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js"></script>
<style>
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
		<h3 class="tittle"><span class="new">HOT</span> 热门音乐</h3>
		<div class="clearfix"> </div>
	</div>
	
	<table class="table table-bordered" align="center">
	  <thead>
		<tr>
		  <th>序号</th>
		  <th>音乐名称</th>
		  <th>播放</th>
		  <th>评分</th>
		</tr>
	  </thead>
	  <tbody>
	<s:if test="#request.hotMusic!=null && #request.hotMusic.size()>0">
    	<s:iterator var="music" value="#request.hotMusic" status="status">
        	<tr>
			  <td><s:property value="#status.index+1"/></td>
			  <td style="width:50%"><s:property value="#music.musicname"/></td>
			  <td><a href="<s:property value="#music.url"/>" target="_blank" >播放</a></td>
			  <td>
			  
  					<div class="score" style="display: inline-block;margin-right:80px">
						<ul class="cleanfloat" style="display: inline-block">
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
	
</div><div class="clearfix"> </div>
<script>
	$(function(){
		
		var scoreTemp = parseInt("${score.point ==null?0:score.point}");
		var countTemp = 0;
		$(".cleanfloat").find("li").each(function(){
			if(countTemp<scoreTemp){
				$(this).css("color", '#f00');
			countTemp++;
			}
		});
		
		$(".cleanfloat").find("li").click(function() {
			if(!loginTemp){
				return false;
			}
			$(this).css("color", '#f00');
			$(this).prevAll().css("color", '#f00');
			$(this).nextAll().css("color", '#ccc');
			var data = "musicid=${music.id}"+"&point="+$(this).attr("data-info");
			common_ajax("addScore",data);
		})
		
	});

</script>