<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,maximum-scale=2" />
	<title>讯宝智慧储物箱</title>

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
    
<style type="text/css">
span.em{ color:#666; }
</style>    

	<script type="text/javascript">
	
	// 所有功能必须包含在 WeixinApi.ready 中进行
	WeixinApi.ready(function(Api){
	    // 隐藏右上角popup菜单入口
	    Api.hideOptionMenu();
	 
	    // 隐藏浏览器下方的工具栏
	    Api.hideToolbar();
	});
	
	
	// 点击列表切换背景颜色
	function clickDiv(id){
		
		$(".list_div").css("background","#fff");
		$(".list_div_"+id).css("background","rgb(239,239,239)");
		
	}
	
	
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
<body>
	<div class="well well-sm text-left">
	<% 
	if (request.getAttribute("type").equals("1")){
		out.print(">>&nbsp;寄件查询结果");
	}else if (request.getAttribute("type").equals("2")){
		out.print(">>&nbsp;收件查询结果");
	}else{
	    out.print(">>&nbsp;寄存查询结果");
	}
	%>
	</div>
	<%
	String boxInfo = (String)request.getAttribute("checkInfo"); 
	if(boxInfo.indexOf(" ") == -1){
	%>
	<div style="margin-top:20px; padding-bottom:20px; text-align: center;">
		<img src="images/error.jpg"/>
	</div>
	<div style="margin-top:30px; padding-bottom:30px; text-align: center; color:#999;">
		<span><%=boxInfo%></span><br/>
		<span>如遇问题请拨打：15026966745</span>
	</div>
	<div style="padding-left: 8px; padding-right:8px;">
		<button type="button" class="btn btn-danger btn-block" style="height:40px;" onclick="backClose()">返回</button>
	</div>
	<%
	}else{
	String []arrayBoxInfo=boxInfo.split(" ");
	int num=Integer.valueOf((arrayBoxInfo[0]));
	%>
	<%
	for (int i=0;i<num;i++) {
	%>
	<div style="padding:8px; border-bottom:1px solid #ccc;" class="list_div list_div_<% out.print(i); %>" onclick="clickDiv(<% out.print(i); %>)" >
		<div>
			<b>物箱地址：</b><span class="em"><% out.print(arrayBoxInfo[6*i+1]); %></span>
		</div>	
		<div>
			<div style="display: block; float:left; margin-right:20px;">
				<b>物箱号：</b><span class="em"><% out.print(arrayBoxInfo[6*i+2]); %></span>
			</div>
			<div>
				<b>交易状态：</b>
				<span class="em">
					<%
					if(arrayBoxInfo[6*i+6].equals("1")){
						out.print("完成");
					}
					if(arrayBoxInfo[6*i+6].equals("2")){
						out.print("已投入物箱");
					}
					if(arrayBoxInfo[6*i+6].equals("3")){
						out.print("撤回");
					}
					if(arrayBoxInfo[6*i+6].equals("4")){
						out.print("已经分配快递员");
					}
					if(arrayBoxInfo[6*i+6].equals("5")){
						out.print("失败");
					}
					%>
				</span>
			</div>
		</div>
		<div>
			<b>订单号：</b><span class="em"><% out.print(arrayBoxInfo[6*i+3]); %></span>
		</div>
		<div>
			<b>订单时间：</b><span class="em"><% out.print(arrayBoxInfo[6*i+4]+" "+arrayBoxInfo[6*i+5]); %></span>
		</div>
	</div>
	<%
	}
	}
	%>
	
</body>
</html>