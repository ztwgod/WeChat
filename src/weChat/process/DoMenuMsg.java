package weChat.process;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import weChat.BaseMsg;
import weChat.MyFileReader;
import weChat.receive.MenuMsg;

public class DoMenuMsg {

	public  String processMsg(MenuMsg menuMsg) {
		String resultMsg = null;
		String content = "";
		int key = Integer.valueOf(menuMsg.getEventKey());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		MyFileReader reader = (MyFileReader) application
				.getAttribute("myFileReader");
		switch (key) {
		case 11:
			content="�ظ�ӰƬ�ؼ���,��ӰƬ�����ѯ��Ӧ����\n"
					+"\n"
					+"ӰƬ�����ѯʾ������:\n"
					+"�ظ�YC2144��ѯYC2(�Ϻ�Ӱ��2��)+14(��ӳ����:14��)+4(���ĳ�)\n"
					+"Ҳ�ɻظ�YC214��ѯ�Ϻ�Ӱ��2��14�յ����г���"
					+"\n"
					+ "��ѯӰԺ����,�Ϻ����ʵ�Ӱ��ʱ���Ʊ���ͻ��·��˵�����\n"
					+ "\n"
					+ "����Ӱ����������:\n";
			int n=0;
			Map<String, String> map = reader.getMyCinemaData();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				n++;
				if (n==4)
					break;
				content = content + entry.getKey() + ":" + entry.getValue()
						+ "\n";
			}
			content=content+"\n��ѯ����Ӱ������,��ظ�\"Ӱ������\"";
			break;
		case 12:
			content = "�Ϻ����ʵ�Ӱ�ڴ�6��14�տ�ʼ,6��22�������������35��ӰԺչ����";
			break;
		case 14:
			content = "��Ʊ���¼һƱͨ�ٷ���վ:www.tickets.com.cn";
			break;
		case 13:
			content = "����ʱ��:\n" 
					+ "��һ����ʼʱ��:  9:00\n" + "�ڶ�����ʼʱ��: 10:00\n" + "��������ʼʱ��: 13:30\n"
					+ "���ĳ���ʼʱ��: 15:45\n" + "���峡��ʼʱ��: 18:30\n"
					+ "��������ʼʱ��: 20:45\n" + "���߳���ʼʱ��: 23:00\n";
			break;
		case 21:
			return new weChat.send.NewsMsg(menuMsg.getFromUserName(),menuMsg.getToUserName(),
					new Date().getTime()+"",BaseMsg.MSG_TYPE_NEWS,"����Ů�������������������Ͻ�����","�й�����ĬƬ"
							+ "����Ů�������޸��潫�����Ϻ�̲����6��15����Ϊ�Ϻ����ʵ�Ӱ�ڵ���ʽչӳ���ڷ�ӳ��һƱͨ�ر��Ƴ�����Ů����"
							+ "ӰƱ������ĬƬ��������ֵ�������ϡ���ӭ���Ӱ���µ硢���������Ŷ�����",
							"http://image.tickets.com.cn/day_140607/201406070111447647.jpg",""
							+ "http://siff.tickets.com.cn/news/detail_4958.html").getNewsMsg();
		case 31:
		/*	create(menuMsg.getFromUserName());
			File f = new File(this.getClass().getClassLoader().getResource("/")
					.getPath().replace("%20", " ")
					+ "aa.png");*/
			content="�����Ż���Ϊ:"+menuMsg.getFromUserName().substring(0,8)+"\n";
			content=content+"ƾ���뵽һƱͨ�ŵ깺���Ӱ����Ʊ������ÿ��Ʊ�Ż�5Ԫ�����һ�4�š�\n";
			content=content+"�û���ս���Ȩ��һƱͨ���С�\n";
					

		}
		resultMsg = new weChat.send.TextMsg(menuMsg.getFromUserName(),
				menuMsg.getToUserName(), new Date().getTime() + "",
				BaseMsg.MSG_TYPE_TEXT, content).getTextMsg();
		return resultMsg;
	}

	/*���ɶ�ά��
	 * public  boolean create(String userName) {
		File f = new File(this.getClass().getClassLoader().getResource("/")
				.getPath().replace("%20"," ")
				+ userName+".png");
		if (!f.exists()) {
			File file = new File(this.getClass().getClassLoader().getResource("/")
					.getPath().replace("%20"," ")
					+ userName+".png");

			QRCodeWriter writer = new QRCodeWriter();
			try {
				// ���ɶ�ά��
				BitMatrix matrix = writer.encode(userName,
						BarcodeFormat.QR_CODE, 200, 200);
				MatrixToImageWriter.writeToFile(matrix, "png", file);
				// ��ȡ��ά��

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else
			return true;*/

}
