<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户登录【在线音乐推荐系统】</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css"/>
	<link href="${pageContext.request.contextPath}/static/css/jquery.validator.min.css" rel="stylesheet">
  </head>
  <body background="${pageContext.request.contextPath}/static/img/front_register_bg.jpg">
  	<div>
		<div class="container" align="center">
  			<div class="panel panel-default main_panel" style="margin-top: 60px;width: 500px;height: 420px;">
  				<div class="panel-body">
			  		<div class="logo" style="font-size:36px;color:#588a9a;padding-bottom: 30px">
			  			用户登录
			  		</div>
				  		<div class="form">
					  		<form data-validator-option="{theme:'bootstrap', timely:2, stopOnError:true}" id="form" method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/doLogin">
					  			 <div class="form-group">
								 	 <div class="input-group input1">
								         <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
								      	 <input class="form-control" type="text" id="username" name="username" maxlength="20" required="required" placeholder="输入用户名">
								     </div>
							     </div>
					  			 <div class="form-group">
								     <div class="input-group input1">
									     <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
									     <input class="form-control" type="password" id="password" name="password" maxlength="20" required="required" placeholder="输入密码">
								     </div>
							     </div>
								 <div>
								     <button class="" id="submitBtn" type="button" style="width: 120px;margin-bottom: 10px">登录</button>
								 </div>
								 <a href="${pageContext.request.contextPath}/register" style="margin-top: 10px;font-size: 10px;color: black;">没有账号，去注册</a>
								 <div style=" color: red; font-size: 18px;"> </div>
				  		    </form>
				  			<hr width="70%" />
				  		</div>
  					<div>
  						<span style="color:#AAA;">—</span>
  						<span class="shadowfont"><a href="${pageContext.request.contextPath}/">在线音乐推荐系统</a></span>
  						<span style="color:#AAA;">—</span>
  					</div>
  				</div>
  			</div>
 		</div>
  	  </div>
	</body>
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.cookie.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validator.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/zh-CN.min.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			$("#submitBtn").on("click",function(){
				if($("#username").val()=="" || $("#password").val()==""){
					layer.msg("数据不能为空!");
				}else{
					var options = {
		                url: $("#form").attr("action"),
		                type: 'post',
		                dataType: 'json',
		                data: $("#form").serialize(),
		                success: function (data) {
	                    	if(data.success=="1"){
	                    		layer.msg("登录成功！");
								setTimeout(function () { location.href="${pageContext.request.contextPath}/index"; }, 1000);
	                    	}else{
	                    		layer.msg("登录失败！用户名或密码错误！");
	                    	}
		                 }
			         };
	                $.ajax(options);
        			return false;
	         	}
			});
		});
</script>
</html>