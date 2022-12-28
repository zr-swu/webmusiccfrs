<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
 <html class="no-js" lang="">
  <head>
    <title>【管理员】在线音乐推荐系统</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style3.css">
    <style>
    	body,html{
			height: 100%;
		}
		.self-a-font{color: #41434d;
		     font-family: 'Open Sans';
		     font-size: 14px;
		     font-weight: 700;
		     line-height: 50px;
		     text-transform: uppercase;
	     }
    </style>
  </head>
  <body>
  	<!-- Left Panel -->
    <%@ include file="index_left.jsp" %>
    <!-- /#left-panel -->
    <!-- Right Panel -->
    <div id="right-panel" class="right-panel"  style="height:90%;background:white">
        <!-- Header-->
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header" style="float:right;">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin"
                    	 style="line-height:20px;float:right;font-size:22px;color:gray;width:300px">在线音乐推荐系统管理员</a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="user-area dropdown float-right">
                    			<a href="javascript:void(null)" class="active" >管理员：${session_admin.adminname }</a>
			                    &nbsp;&nbsp;&nbsp;&nbsp;
			                    <a href="${pageContext.request.contextPath}/admin_index_quit" class="active" >注销</a>
                    </div>
                </div>
            </div>
        </header>
        <!-- /#header -->
        <!-- Content -->
		<div class="content" style="width:100%;height:100%">
	        	<iframe style="width:100%;height:100%" name="admin_iframe" src="${pageContext.request.contextPath}/admin_index_content" frameborder="no" border="0" marginheight="0" marginwidth="0" scrolling="auto">
				</iframe>
        </div>
        <!-- /.content -->
    </div>
    <!-- /#right-panel -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
  	<script type="text/javascript">
  			//控制侧边栏
			$(function(){
				$(".dropdown-toggle").click(function(){
					var temp = $(this);
					var flag = false;
					if(temp.parent().hasClass("show")){
						flag = true;
					}
					$(".menu-item-has-children").each(function(){
						$(this).removeClass("show");
						$(this).find("ul").removeClass("show");
					});
					if(!flag){
						temp.parent().addClass("show");
						temp.parent().find("ul").addClass("show");
					}
				});
			});
  	</script>
  </body>
</html>