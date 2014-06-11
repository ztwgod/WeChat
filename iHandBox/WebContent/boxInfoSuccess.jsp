<%@ page language="java" import="java.net.URLEncoder" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	
	
	
	var pwd = null;
	function passWord(id)
	{
		var transId=document.getElementById(id).getAttribute("value");
		pwd = document.getElementById("pwvalue"+id).value;
		if(pwd == null || pwd == ""){
			alert("开箱密码不能为空！");
		}else if(pwd.match(/^[0-9][0-9]{7}$/) == null){
			alert("开箱密码格式不正确！");
		}else{
			window.location.href="openbox?msg="+transId+"&pwd="+pwd;
		}
	}
	
	function mimaBlock(id){
		
		$(".mima_div").css("display","none");
		$(".mima_div_"+id).css("background","#fff");
		$(".list_div").css("background","#fff");
		$(".mima_btn").css("display","block");
		
		$(".mima_div_"+id).css("display","block");
		$(".mima_div_"+id).css("background","rgb(239,239,239)");
		$(".list_div_"+id).css("background","rgb(239,239,239)");
		$(".mima_btn_"+id).css("display","none");
		
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

	<style type="text/css">
	span.em{ color:#666; }
	</style>
	
</head>
<body>
	<div class="well-sm well text-left">>>&nbsp;选择要打开的物箱</div>
	<%
		String boxInfo = (String) request.getAttribute("boxInfo");
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
		String[] arrayBoxInfo = boxInfo.split(" ");
		int num = Integer.valueOf((arrayBoxInfo[0]));
	%>

	<%
		for (int i = 0; i < num; i++) {
	%>
	<div class="list_div list_div_<% out.print(i); %>" style="border-top:1px solid #ccc; padding:8px; position: relative;" id="<% out.print(i); %>" value="<%out.print(arrayBoxInfo[i * 6 + 3]);%>" onclick="mimaBlock(<% out.print(i); %>)">
		<div>
			<b>物箱地址：</b><span class="em"><% out.print(arrayBoxInfo[i * 6 + 1]); %></span>
		</div>	
		<div>
			<div style="display: block; float:left; margin-right:20px;">
				<b>物箱号：</b><span class="em"><% out.print(arrayBoxInfo[i * 6 + 2]); %></span>
			</div>
			<div>
				<b>交易状态：</b>
				<span class="em">
				<%
					if(arrayBoxInfo[i * 6 + 6].equals("1")){
						out.print("完成");
					}
					if(arrayBoxInfo[i * 6 + 6].equals("2")){
						out.print("已投入物箱");
					}
					if(arrayBoxInfo[i * 6 + 6].equals("3")){
						out.print("撤回");
					}
					if(arrayBoxInfo[i * 6 + 6].equals("4")){
						out.print("已经分配快递员");
					}
					if(arrayBoxInfo[i * 6 + 6].equals("5")){
						out.print("失败");
					}
				%>
				</span>
			</div>
		</div>
		<div>
			<b>订单号：</b><span class="em"><% out.print(arrayBoxInfo[i * 6 + 3]); %></span>
		</div>
		<div>
			<b>订单时间：</b><span class="em"><% out.print(arrayBoxInfo[i * 6 + 4] + " "+ arrayBoxInfo[i * 6 + 5]); %></span>
		</div>
		<button type="button" class="btn btn-default mima_btn mima_btn_<% out.print(i); %>" style="position: absolute; right:8px; top:40px; background:rgb(240,171,32); border:none;" onclick="mimaBlock(<% out.print(i); %>)">开箱</button>
	</div>
	
	<!-- 开箱密码  -->
	<div class="mima_div mima_div_<% out.print(i); %>" style=" padding-bottom: 10px; padding-left:8px; padding-right:70px; position: relative; display:none;">
		<input type="text" class="form-control" id="pwvalue<% out.print(i); %>" style="width:100%;" placeholder="请输入开箱密码" />
		<button type="submit" class="btn btn-primary" style="position: absolute; top:0px; right:8px;" onclick="passWord(<% out.print(i); %>)">确定</button>
	</div>
	<!-- 开箱密码 END -->
	<%
		}
		}
	%>

</body>
</html>