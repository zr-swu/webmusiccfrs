<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户注册【在线音乐推荐系统】</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css"/>
	<link href="${pageContext.request.contextPath}/static/css/jquery.validator.min.css" rel="stylesheet">
	<style>
		.self_input{
			background-color:white;border:0;
			width:100px
		}
		.input1{
			margin-top: 0px
		}
		.input-group .form-control{
			width:90%
		}
		.layui-layer-rim{
			text-align:center;
			line-height:180px
		}
	</style>
	</head>
	<body background="${pageContext.request.contextPath}/static/img/front_register_bg.jpg" 
		style="overflow: auto;">
		<br/><br/>
		<div>
			<div class="container" align="center">
			  	<div class="panel panel-default main_panel" style="width:700px;height:auto;margin-top: 30px">
			  		<div class="panel-body">
			  		<div class="logo" style="font-size:36px;color:#588a9a;padding-bottom: 30px">
			  			用户注册
			  		</div>
			  		<div class="form">
				  		<form data-validator-option="{theme:'bootstrap', timely:2, stopOnError:true}" id="form" method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/doRegister">
				  			 <div class="form-group">
							    <div class="input-group input1">
							      <div class="input-group-addon self_input" >用户名：</div>
							      <input class="form-control" type="text" id="username" name="username" maxlength="20" required="required" placeholder="输入用户名" value="${user.username}">
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="input-group input1">
							      <div class="input-group-addon self_input" >邮箱：</div>
							      <input class="form-control" type="text" id="email" name="email" maxlength="20" required="required" placeholder="输入邮箱" value="${user.email}">
							    </div>
							  </div>
				  			 <div class="form-group">
							    <div class="input-group input1">
							      <div class="input-group-addon self_input">密码：</div>
							      <input class="form-control" type="password" id="password" name="password" maxlength="20" required="required" placeholder="输入密码">
							    </div>
							 </div>
							 <div class="form-group">
							    <div class="input-group input1">
							      <div class="input-group-addon self_input">确认密码：</div>
							      <input class="form-control" type="password" id="passwordConf" name="passwordConf" maxlength="20" required="required" placeholder="输入确认密码">
							    </div>
							 </div>
							  <div>
							  	<button class="" id="submitBtn" type="button" style="width: 120px;margin-bottom: 10px">注册</button>
							  </div>
							  <a href="${pageContext.request.contextPath}/login" style="margin-top: 10px;font-size: 10px;color: black;">已有账号，去登录</a>
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
				var temp = true;
				$("form input").each(function(){
					if($(this).val()==null || $(this).val()==""){
						temp = false;
						return false;
					}
				});
				if(!temp){
					layer.msg("数据不能为空!");
				}else{
					if($("#password").val()!=$("#passwordConf").val()){
						layer.msg("两次密码不一致!");return;
					}
	            	var options = {
		                url: $("#form").attr("action"),
		                type: 'post',
		                dataType: 'json',
		                data: $("#form").serialize(),
		                success: function (data) {
		                    if(data.success==-1){
		                    	layer.msg("用户名已存在！");
		                    }else{
		                    	if(data.success==0){
		                    		layer.msg("注册失败！");
		                    	}else{
		                    		layer.open({
										  type: 1,
										  skin: 'layui-layer-rim', //加上边框
										  shade: false,
										  title: false, //不显示标题
										  area: ['400px', '200px'], //宽高
										  content: "注册成功！<a href='${pageContext.request.contextPath}/login'>点击登录</a>", //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
										});
		                    	}
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