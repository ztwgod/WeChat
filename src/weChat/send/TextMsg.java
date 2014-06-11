package weChat.send;

import weChat.BaseMsg;

public class TextMsg extends BaseMsg{
	private String content;
	private String textMsg="<xml>"+  
						   "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
						   "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
						   "<CreateTime>%3$s</CreateTime>"+  
						   "<MsgType><![CDATA[%4$s]]></MsgType>"+  
						   "<Content><![CDATA[%5$s]]></Content>"+ 
						   "</xml>";
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public TextMsg(String toUserName,String fromUserName,String createTime,
				   String msgType,String content)
	{
		textMsg=String.format(textMsg, toUserName, fromUserName, createTime,
				              msgType, content);  
		
	}
	public String getTextMsg()
	{
		return textMsg;
	}
	

}
