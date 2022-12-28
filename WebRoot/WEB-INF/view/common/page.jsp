<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${pageBean!=null && pageBean.list!=null}">
		<div style="color:#333;font-size: 15px;text-align: center;padding-top:10px">
			<span >
				共${pageBean.totalCount}条数据&nbsp;
				<c:if test="${pageBean.totalPage!=0}">
					第${pageBean.page}/${pageBean.totalPage}页&nbsp; 
				</c:if>
			</span>
			<c:if test="${pageBean.page!=1 && pageBean.totalPage!=0}">
				<a href="javascript:toPage(1)">首页</a>&nbsp; 
				<a href="javascript:toPage(${pageBean.page-1})">上一页 </a>&nbsp; 
			</c:if>
			<c:if test="${pageBean.page!=pageBean.totalPage && pageBean.totalPage!=0}">
				<a href="javascript:toPage(${pageBean.page+1})">下一页 </a>&nbsp;
				<a href="javascript:toPage(${pageBean.totalPage})">尾页</a>
			</c:if>
		</div>
  	</c:if>
  	<script>
  		function toPage(page){
  			$("#page").val(page);
  			$("#form").submit();
  		}
  	</script>