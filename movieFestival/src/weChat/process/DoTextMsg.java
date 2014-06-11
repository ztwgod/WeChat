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
				res = "�Բ�������ѯ��Ӱ��û�в��뱾���Ϻ����ʵ�Ӱ�ڷ�ӳ�������·��˵���ѯ���в����Ӱ�ڷ�ӳӰ������";
			} else {
				res="�ظ����α�ſ��Բ鿴��Ӧ������ϸ��Ϣ\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + entry.getKey().trim() + ":"
							+ entry.getValue().getMovieName() + " "
							+ entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		} else if (content.equals("Ӱ������")) {
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
				res = "�Բ�������ѯ��Ӱ������û�в�չӰƬ���ţ���ֱ�ӻظ�Ӱ�������ѯ��ӳ����";
			} else {
				res="�ظ����α�ſ��Բ鿴��Ӧ������ϸ��Ϣ\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + entry.getKey().trim() + " ӰƬ����:"
							+ entry.getValue().getMovieName() + " ʱ��:"
							+ entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		} else if (content.matches("[a-zA-Z]{2,4}[0-9]{4}")) {
			Map<String, MovieInfo> result = my.myIdQuery(content);
			if (result.isEmpty()) {
				res = "�Ҳ���û�ж�Ӧ���α�ţ���鿴�����Ƿ���ȷ������·���ѯ�˵�����Բ�ѯӰ�����";
			} else {
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + "���α��:" + entry.getKey().trim() + "\n"
							+ "ӰƬ����:" + entry.getValue().getMovieName() + "\n"
							+ "ʱ��:" + entry.getValue().getShowTime() + "\n"
							+ "����:" + entry.getValue().getNationality() + "\n"
							+ "����:" + entry.getValue().getDirector() + "\n"
							+ "��Ա:" + entry.getValue().getActor() + "\n";
					res=res+"\n";
				}
				res=res+"��Ʊ���¼һƱͨ�ٷ���վ:www.tickets.com.cn\n";
			}
		} else if (content.matches("[^\u4E00-\u9FA5]+")) {
			res = "��ӭ�����Ϻ���Ӱ�ڹٷ�ָ��Ʊ��һƱͨ�������ѯ��Ʊ�����·��˵�����Ҳ��ֱ�ӻظ�ӰƬ�����ӰƬ���ֽ��в�ѯ��";
		} else {
			Map<String, MovieInfo> result = my.myFilmQuery(content);
			if (result.isEmpty()) {
				res = "û�в�ѯ�������ùؼ��ֵ�ӰƬ�������������ؼ���";
			} else {
				res="�ظ����α�ſ��Բ鿴��Ӧ������ϸ��Ϣ\n";
				res=res+"\n";
				res = res + "ӰƬ��Ϣ:\n";
				res=res+"\n";
				for (Map.Entry<String, MovieInfo> entry : result.entrySet()) {
					res = res + "���α��:" + entry.getKey().trim() + "\n"
							+ "ӰƬ����:" + entry.getValue().getMovieName() + "\n"
							+ "ʱ��:" + entry.getValue().getShowTime() + "\n";
					res=res+"\n";
				}
			}
		}
		return new weChat.send.TextMsg(msg.getFromUserName(),
				msg.getToUserName(), new Date().getTime() + "",
				msg.getMsgType(), res).getTextMsg();
	}
}
