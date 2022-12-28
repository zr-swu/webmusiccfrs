<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${pageBeanEx!=null && pageBeanEx.list!=null}">
		<div style="color:#333;font-size: 15px;text-align: center;padding-top:10px">
			<span >
				共${pageBeanEx.totalCount}条数据&nbsp;
				<c:if test="${pageBeanEx.totalPage!=0}">
					第${pageBeanEx.page}/${pageBeanEx.totalPage}页&nbsp; 
				</c:if>
			</span>
			<c:if test="${pageBeanEx.page!=1 && pageBeanEx.totalPage!=0}">
				<a href="javascript:toPage(1)">首页</a>&nbsp; 
				<a href="javascript:toPage(${pageBeanEx.page-1})">上一页 </a>&nbsp; 
			</c:if>
			<c:if test="${pageBeanEx.page!=pageBeanEx.totalPage && pageBeanEx.totalPage!=0}">
				<a href="javascript:toPage(${pageBeanEx.page+1})">下一页 </a>&nbsp;
				<a href="javascript:toPage(${pageBeanEx.totalPage})">尾页</a>
			</c:if>
		</div>
  	</c:if>
  	<script>
  		function toPage(page){
  			$("#page").val(page);
  			$("#form").submit();
  		}
  	</script>