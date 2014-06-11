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
					new Date().getTime()+"",BaseMsg.MSG_TYPE_TEXT,"��ӭ��ע17���Ӱ�ڹٷ�ָ��΢�ź�һƱͨ\n"
							+ "\n"
							+ "ֱ�ӻظ�ӰƬ�ؼ���(���� ������ )��ѯӰƬ����\n"
							+ "\n"
							+ "Ҳ�ɻظ�\"ӰԺ����+����+����\"��ѯӰƬ����\n"
							+ "����ظ� DGM112 ��ѯ�����(DGM)1��(1)12��(12)�����г���\n"
							+ "\n"
							+ "���ɻظ� DGM1 ��ѯ�����(DGM)1��(1)�����г���\n"
							+ "\n"
							+ "��ѯӰԺ����,�Ϻ����ʵ�Ӱ��ʱ��Ͷ�Ʊ���ͻ��·��˵�����\n"))
					.getTextMsg();
		}
		return resultMsg;
		
	}
	

}
