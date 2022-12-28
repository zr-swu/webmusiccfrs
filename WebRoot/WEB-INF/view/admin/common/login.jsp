<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>管理员登录【在线音乐推荐系统】</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css"/>
	<link href="${pageContext.request.contextPath}/static/css/jquery.validator.min.css" rel="stylesheet">
  </head>
  <body background="${pageContext.request.contextPath}/static/img/lgbg.jpg">
		<div>
	<div class="container" align="center">
	
  	<div class="panel panel-default main_panel" style="margin-top: 60px;width: 500px;height: 420px;">
  		<div class="panel-body">
  		<div class="logo" style="font-size:28px;color:#588a9a;padding-bottom: 30px">
  			在线音乐推荐系统<br>管理员登录
  		</div>
  		<div class="form">
  		<form data-validator-option="{theme:'bootstrap', timely:2, stopOnError:true}" id="form" method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin_index_doLogin">
  			 <div class="form-group">
			    <div class="input-group input1">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
			      <input class="form-control" type="text" id="adminname" name="adminname" value="admin" maxlength="20" required="required" placeholder="输入管理员名">
			    </div>
			  </div>
  			 <div class="form-group">
			    <div class="input-group input1">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
			      <input class="form-control" type="password" id="password" name="password" value="admin" maxlength="20" required="required" placeholder="输入密码">
			    </div>
			    </div>
			  <div>
			  <button class="" id="submitBtn" type="button" style="width: 120px;margin-bottom: 10px">登录</button>
			  </div>
  		</form>
  		<hr width="70%" />
  		</div>
  		<div><span style="color:#AAA;">—</span><span class="shadowfont"><a href="${pageContext.request.contextPath}/">在线音乐推荐系统</a></span><span style="color:#AAA;">—</span></div>
  	</div>
  		</div>
  		</div>
  		</div>
		
	</body>
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
  <script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
  <script>
	$(function(){
		$("#submitBtn").on("click",function(){
				var temp = true;
				$("form input[type='text']").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if($("#password").val()==null || $("#password").val()==""){
					temp = false;
				}
				if(!temp){
					layer.msg("数据不能为空!");return false;
				}
					var options = {
		                url: $("#form").attr("action"),
		                type: 'post',
		                dataType: 'json',
		                data: $("#form").serialize(),
		                success: function (data) {console.info(data);
	                    	if(data.success>0){
	                    		layer.msg("操作成功！正在跳转页面...");
	                    		setTimeout(function () {location.href="${pageContext.request.contextPath}/"+data.url; }, 1000);
	                    	}else{
	                    		layer.msg("操作失败！");
	                    	}
		                 }
			         };
	                $.ajax(options);
        			return false;
		});
	});
</script>
</html>