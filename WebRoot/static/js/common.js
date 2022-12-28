//公共ajax提交数据
function common_ajax(url,data){
  		var options = {
           url: basePath+url,
           type: 'post',
           dataType: 'json',
           data:data,
           success: function (data) {
              	if(data.success>0){
              		layer.msg("操作成功！");
              		if(data.url!=null && data.url!=""){
              			setTimeout(function () {location.href=basePath+data.url; }, 1000);
              		}
              	}else{
              		layer.msg("操作失败！");
              	}
            }
     };
     $.ajax(options);
}

function common_ajax_other(url,data){
	var options = {
   url: basePath+url,
   type: 'post',
   dataType: 'json',
   data:data,
   success: function (data) {
      	if(data.success>0){
      		layer.msg("操作成功！");
      		if(data.url!=null && data.url!=""){
      			setTimeout(function () {location.href=basePath+data.url; }, 1000);
      		}
      	}else{
      		var msg = data.msg;
      		if(msg!=null && msg!=""){
      			layer.msg(msg);
      		}else{
      			layer.msg("操作失败！");
      		}
      	}
    }
};
$.ajax(options);
}

$(".do_before_login").click(function(){
	if(cUsername==null || cUsername==""){
		layer.open({
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  shade: false,
		  title: false, //不显示标题
		  area: ['400px', '200px'], //宽高
		  content: "请先登录！<a href='"+basePath+"/login'>点击登录!</a> &nbsp;&nbsp;&nbsp;&nbsp;" +
	  		"<a href='"+basePath+"/register'>没有账号，点击注册!</a>", //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	}else{
		location.href=$(this).attr("data-href");
	}
});

$(".do_before_login_other").click(function(){
	if(cUsername==null || cUsername==""){
		loginTemp = false;
		layer.open({
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  shade: false,
		  title: false, //不显示标题
		  area: ['400px', '200px'], //宽高
		  content: "<a href='"+basePath+"/login'>请先登录！点击登录!</a> &nbsp;&nbsp;&nbsp;&nbsp;" +
		  		"<a href='"+basePath+"/register'>没有账号，点击注册!</a>", //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
		return false;
	}else{
		loginTemp = true;
		return true;
	}
});
	