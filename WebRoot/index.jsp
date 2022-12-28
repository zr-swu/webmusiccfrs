<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
 <title>Home</title>
 </head> 
 <body>
    <%response.sendRedirect(basePath+"index"); %>
 </body>
</html>