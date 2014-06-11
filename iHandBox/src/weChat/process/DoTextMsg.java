package weChat.process;

import java.io.IOException;
import java.util.Date;

import weChat.BaseMsg;
import weChat.Sim;
import weChat.receive.TextMsg;

public class DoTextMsg extends BaseMsg implements DoMsg{   
	/**
	 * function:TextMsg process TextMsg
	 * @param msg
	 * @return
	 */
	public static String processMsg(TextMsg msg) {
		String content="moren";
		if (msg.getContent().equals("���"))
			content="��ӭ��Ѹ��΢��ƽ̨";
		else {	
			try {
				content = Sim.simTalk(msg.getContent());
			} 	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  new weChat.send.TextMsg(msg.getFromUserName(), msg.getToUserName(), 
										new Date().getTime()+"", msg.getMsgType(), 
										content).getTextMsg();
	}
}
