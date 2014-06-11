<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,maximum-scale=2" />
	<title>手机开箱</title>

	<script src="js/weixinjsapi.js"></script>
	<!-- Bootstrap -->
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script type="text/javascript">
	
		// 所有功能必须包含在 WeixinApi.ready 中进行
		WeixinApi.ready(function(Api){
		    // 隐藏右上角popup菜单入口
		    Api.hideOptionMenu();
		 
		    // 隐藏浏览器下方的工具栏
		    Api.hideToolbar();
		});
	
		
		// 验证手机号码非法性
		function checkNum(){
			var num = document.getElementById("telephone").value;
			if( num == null || num == ""){
				alert("手机号码不能为空！");
				return false;
			}
			if(num.match(/^(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$/) == null){
				alert("手机号码格式不正确！");
				return false;
			}else{
				return true;
			}
		}
	</script>  
    
</head>
<body>
	<div class="well well-sm text-left">>>&nbsp;手机开箱</div>
	<div style="width:100%; text-align:center;">
		<img style="width:226px;" src="../WeChat/images/xb-logo.png" class="img-rounded"/>
	</div>
	
	<form role="form" action="getboxinfo" method="post" style="width:96%; margin:0 auto;">
		<div class="form-group">
			<label>手机号</label>
			<input type="text" class="form-control" id="telephone" name="telephone" placeholder="请输入手机号" style="height:40px"/>
		</div>
		<button type="submit" class="btn btn-primary btn-block" style="height:40px" onclick="return checkNum();">提交</button>
	</form>
</body>
</html>