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
					new Date().getTime()+"",BaseMsg.MSG_TYPE_TEXT,"欢迎关注17届电影节官方指定微信号一票通\n"
							+ "\n"
							+ "直接回复影片关键字(例如 蝙蝠侠 )查询影片场次\n"
							+ "\n"
							+ "也可回复\"影院代码+厅号+日期\"查询影片场次\n"
							+ "例如回复 DGM112 查询大光明(DGM)1厅(1)12号(12)的所有场次\n"
							+ "\n"
							+ "还可回复 DGM1 查询大光明(DGM)1厅(1)的所有场次\n"
							+ "\n"
							+ "查询影院代码,上海国际电影节时间和订票请猛击下方菜单栏！\n"))
					.getTextMsg();
		}
		return resultMsg;
		
	}
	

}
