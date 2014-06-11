package weChat.process;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import weChat.BaseMsg;
import weChat.MyFileReader;
import weChat.pojo.MovieInfo;
import weChat.receive.TextMsg;

public class DoTextMsg extends BaseMsg implements DoMsg {
	/**
	 * function:TextMsg process TextMsg
	 * 
	 * @param msg
	 * @return
	 */
	public static String processMsg(TextMsg msg) {
		String content = msg.getContent();
		String res = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		MyFileReader my = (MyFileReader) application
				.getAttribute("myFileReader");

		if (content.matches("[a-zA-Z]{2,4}[0-9]{1}")) {
			Map<String, MovieInfo> result = my.myIdQuery(content);
			if (result.isEmpty()) {
				res = "对不起，您查询的影厅没有参与本次上海国际电影节放映，请点击下方菜单查询所有参与电影节放映影厅代码";
			} else {
				res="回复场次编号可以查看对应场次详细信息\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + entry.getKey().trim() + ":"
							+ entry.getValue().getMovieName() + " "
							+ entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		} else if (content.equals("影厅代码")) {
			MyFileReader reader = (MyFileReader) application
					.getAttribute("myFileReader");
			Map<String, String> map = reader.getMyCinemaData();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				res = res + entry.getKey() + ":" + entry.getValue()
						+ "\n";
			}
		} else if (content.matches("[a-zA-Z]{2,4}[0-9]{3}")) {
			Map<String, MovieInfo> result = my.myIdQuery(content);
			if (result.isEmpty()) {
				res = "对不起，您查询的影厅该日没有参展影片播放，请直接回复影厅代码查询放映场次";
			} else {
				res="回复场次编号可以查看对应场次详细信息\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + entry.getKey().trim() + " 影片名称:"
							+ entry.getValue().getMovieName() + " 时间:"
							+ entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		} else if (content.matches("[a-zA-Z]{2,4}[0-9]{4}")) {
			Map<String, MovieInfo> result = my.myIdQuery(content);
			if (result.isEmpty()) {
				res = "找不到没有对应场次编号！请查看输入是否正确，点击下方查询菜单查可以查询影厅编号";
			} else {
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + "场次编号:" + entry.getKey().trim() + "\n"
							+ "影片名称:" + entry.getValue().getMovieName() + "\n"
							+ "时间:" + entry.getValue().getShowTime() + "\n"
							+ "国家:" + entry.getValue().getNationality() + "\n"
							+ "导演:" + entry.getValue().getDirector() + "\n"
							+ "演员:" + entry.getValue().getActor() + "\n";
					res=res+"\n";
				}
				res=res+"订票请登录一票通官方网站:www.tickets.com.cn\n";
			}
		} else if (content.matches("[^\u4E00-\u9FA5]+")) {
			res = "欢迎来到上海电影节官方指定票务一票通，如需查询或订票请点击下方菜单栏，也可直接回复影片代码或影片名字进行查询！";
		} else {
			Map<String, MovieInfo> result = my.myFilmQuery(content);
			if (result.isEmpty()) {
				res = "没有查询到包含该关键字的影片！请试试其他关键字";
			} else {
				res="回复场次编号可以查看对应场次详细信息\n";
				res=res+"\n";
				res = res + "影片信息:\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + "场次编号:" + entry.getKey().trim() + "\n"
							+ "影片名称:" + entry.getValue().getMovieName() + "\n"
							+ "时间:" + entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		}
		return new weChat.send.TextMsg(msg.getFromUserName(),
				msg.getToUserName(), new Date().getTime() + "",
				msg.getMsgType(), res).getTextMsg();
	}
}
