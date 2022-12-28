<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap3.min.css">
	<style>
		.self_btn{
			border-radius: 3px;
			color: #fff;
		    background-color: #d06565;
 		    border-color: #17a2b8;
		    border: 1px solid transparent;
		    cursor: pointer;
		}
	</style>
  </head>
  <body>
    <div class="col-lg-6" style="max-width: 100%;padding:0">
    	<form action="${pageContext.request.contextPath}/admin_score_list" method="post" id="form">
    		  <input type="hidden" name="pageBean.page" value="${pageBean.page}" id="page">
		      <div class="card">
		          <div class="card-header" style="padding: 0.53rem 1.25rem;">
		              <strong class="card-title">音乐评分列表</strong>
		              <div style="display:inline-block;padding-left:70px">	
			          	  <%--<button type="button" class="self_btn" 
			          	    onclick="javascript:location.href='${pageContext.request.contextPath}/admin_music_addOrUpdate'">添加音乐</button>
		              --%></div>
		          </div>
		          <div class="card-body">
		              <table class="table table-bordered" style="text-align: center;">
		                  <thead>
		                      <tr>
		                          <th scope="col">序号</th>
		                          <th scope="col">用户名称</th>
		                          <th scope="col">音乐名称</th>
		                          <th scope="col">分数</th>
		                          <th scope="col">操作</th>
		                      </tr>
		                  </thead>
		                  <tbody>
		                  	<s:if test="pageBean!=null && pageBean.list!=null">
		                  		<s:iterator var="score" value="pageBean.list" status="status">
		                  			<tr>
			                      		<td><s:property value="#status.index+1"/></td>
			                      		<td><s:property value="#score.user.username"/></td>
			                      		<td><s:property value="#score.music.musicname"/></td>
			                      		<td><s:property value="#score.point"/></td>
			                      		<td>
			                      			<button type="button" class="self_btn" style="background-color:#2d6390"
			              					onclick="javascript:doDelete('${pageContext.request.contextPath}/admin_score_delete?scoreid=<s:property value="#score.id"/>')">删除</button>
			                      		</td>
			                      	</tr>
		                  		</s:iterator>
		                    </s:if>
		                  </tbody>
		              </table>
		          </div>
		     	</div>
		     	<%@include file="../common/page.jsp" %>
      </form>
  </div>
  </body>
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
  <script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
      <script src="${pageContext.request.contextPath}/static/js/common.js"></script>
    <script>
  	var basePath = "${pageContext.request.contextPath}/";
	function doDelete(url){
		//询问框
		layer.confirm('确认删除？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
		  common_ajax(url);
		}, function(){
		  
		});
	}  	
  </script>
</html>