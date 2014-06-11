package weChat.send;

import weChat.BaseMsg;

/**
 * 
 * @author lynn
 * ͼ����Ϣ
 */
public class NewsMsg extends BaseMsg {
		
	String newsMsg ="<xml>"+  
				   "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
				   "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
				   "<CreateTime>%3$s</CreateTime>"+  
				   "<MsgType><![CDATA[%4$s]]></MsgType>"+  
				   "<ArticleCount>1</ArticleCount>"+ 
				   "<Articles>"+ 
				   "<item>"+ 
				   "<Title><![CDATA[%5$s]]></Title>"+ 
				   "<Description><![CDATA[%6$s]]></Description>"+ 
				   "<PicUrl><![CDATA[%7$s]]></PicUrl>"+ 
				   "<Url><![CDATA[%8$s]]></Url>"+ 
				   "</item>" +
				   "</Articles>"+ 
				   "</xml>";
	
	
	public NewsMsg(String toUserName,String fromUserName,String createTime,
			   String msgType,String title,String desc,String picurl,String url){
		newsMsg=String.format(newsMsg, toUserName, fromUserName, createTime, 
			              msgType, title,desc,picurl,url);  
	
	}
	
	
	
	public String getNewsMsg(){
		return newsMsg;
	}
	
	
	
	
	
	
	

}
