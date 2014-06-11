<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>开箱成功</title>

	<script src="js/weixinjsapi.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	
	<script type="text/javascript">
	
		// 所有功能必须包含在 WeixinApi.ready 中进行
		WeixinApi.ready(function(Api){
		    // 隐藏右上角popup菜单入口
		    Api.hideOptionMenu();
		 
		    // 隐藏浏览器下方的工具栏
		    Api.hideToolbar();
		});
		
		
		function backClose(){
			
			// 关闭PC浏览器
			var ua=navigator.userAgent 
			var ie=navigator.appName=="Microsoft Internet Explorer"?true:false 
			if(ie) 
			{ 
			   var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))) 
			   if(IEversion< 5.5) 
			   { 
			   var str = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">' 
			     str += '<param name="Command" value="Close"></object>'; 
			     document.body.insertAdjacentHTML("beforeEnd", str); 
			     document.all.noTipClose.Click(); 
			   } 
			    else 
			   { 
			     parent.window.opener =null;
			     parent.window.open('','_self','');//for IE7
			     parent.window.close(); 
			   } 
			} 
			else 
			{ 
				parent.window.opener =null;
			    parent.window.open('','_self','');//for IE7
			    parent.window.close(); 
			} 
			
			// 关闭微信内置浏览器
			WeixinJSBridge.call('closeWindow');
		}
		
	
	</script>
		
</head>
<body style="padding-left:8px; padding-right:8px;">
	<div style="margin-top:20px; padding-bottom:20px; text-align: center;">
		<img src="images/success.png"/>
	</div>
	<div style="margin-top:30px; padding-bottom:30px; text-align: center; color:#999;">
		<span>远程开箱已执行，请尽快取走您的物品。</span><br/>
		<span>如遇问题请拨打：15026966745</span>
	</div>
	<div>
		<button type="button" class="btn btn-danger btn-block" style="height:40px;" onclick="backClose()">返回</button>
	</div>
</body>
</html>
