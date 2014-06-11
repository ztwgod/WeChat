/**
 * @author ztwgod
 * @date 2014/4/21
 * process the message,decide how to response to the Msg according to the MsgType
 */

package weChat;

import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import weChat.process.DoEventMsg;
import weChat.process.DoMenuMsg;
import weChat.process.DoTextMsg;
import weChat.receive.EventMsg;
import weChat.receive.MenuMsg;
import weChat.receive.TextMsg;
public class MsgDealer {
/**
 *  Function:MsgDealer
 *  @author ztwgod
 *  @param postStr
 *  @return
 */
	public String msgDeal(String postMsg) throws UnsupportedEncodingException {
		Document document=null;  
	    try {  
	    	document = DocumentHelper.parseText(postMsg);  
	    }catch(Exception e){  
	    	e.printStackTrace();  
	    }  
	    Element root=document.getRootElement();
	    String msgType=root.elementText("MsgType");
	    String fromUserName=root.elementText("FromUserName");  
    	String toUserName=root.elementText("ToUserName");
    	String createTime=root.elementText("CreateTime");
	 	String resultMsg=null;
	 	
	    if (msgType.equals(BaseMsg.MSG_TYPE_TEXT)) {
	    	String content=new String(root.elementTextTrim("Content").getBytes(), "UTF-8");   
	    	TextMsg textMsg=new TextMsg(toUserName,fromUserName,
	    				    createTime,msgType,content);
	    	resultMsg=DoTextMsg.processMsg(textMsg);
	    	return resultMsg;
		}
	    else if (msgType.equals(BaseMsg.MSG_TYPE_EVENT_)) {
	    	String event=root.elementText("Event");
	    	if (event.equals("subscribe")||event.equals("unsubscribe")) {
	    		EventMsg eventMsg=new EventMsg(toUserName,fromUserName,
    				    createTime,msgType,event);
	    		resultMsg=DoEventMsg.processMsg(eventMsg);
	    	}
	    	else if (event.equals("click")) {
	    		String eventKey=root.elementText("EventKey");
	    		MenuMsg menuMsg=new MenuMsg(toUserName,fromUserName,
    				    createTime,msgType,event,eventKey);
	    		resultMsg=DoMenuMsg.processMsg(menuMsg);
	    	}
	    	return resultMsg;
	    }
	    else {
	    	return "error";
	    }
	
	}
}
