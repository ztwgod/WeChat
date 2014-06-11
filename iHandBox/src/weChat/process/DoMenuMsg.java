package weChat.process;

import java.util.Date;

import weChat.BaseMsg;
import weChat.receive.MenuMsg;

public class DoMenuMsg {

	public static String processMsg(MenuMsg menuMsg) {
		String resultMsg=null;
		if (menuMsg.getEventKey().equals("11")) {
		}
		resultMsg=  new weChat.send.TextMsg(menuMsg.getFromUserName(), menuMsg.getToUserName(), 
				new Date().getTime()+"",BaseMsg.MSG_TYPE_TEXT, 
				"该功能还在完善中").getTextMsg();
		return resultMsg;
	}

}
