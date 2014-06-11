package weChat.process;

import java.util.Date;

import weChat.BaseMsg;
import weChat.receive.EventMsg;
import weChat.send.TextMsg;

public class DoEventMsg {
	public static String processMsg(EventMsg eventMsg) {
		String resultMsg=null;
		if (eventMsg.getEvent().equals("subscribe"));
		{
			resultMsg= (new TextMsg(eventMsg.getFromUserName(),eventMsg.getToUserName(),
					new Date().getTime()+"",BaseMsg.MSG_TYPE_TEXT,"��ӭ����Ѷ����˾΢��ƽ̨!"))
					.getTextMsg();
		}
		return resultMsg;
		
	}
	

}
