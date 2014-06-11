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
			content="回复影片关键字,或影片代码查询相应场次\n"
					+"\n"
					+"影片代码查询示例如下:\n"
					+"回复YC2144查询YC2(上海影城2厅)+14(放映日期:14日)+4(第四场)\n"
					+"也可回复YC214查询上海影城2厅14日的所有场次"
					+"\n"
					+ "查询影院代码,上海国际电影节时间或订票请猛击下方菜单栏！\n"
					+ "\n"
					+ "部分影厅代码如下:\n";
			int n=0;
			Map<String, String> map = reader.getMyCinemaData();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				n++;
				if (n==4)
					break;
				content = content + entry.getKey() + ":" + entry.getValue()
						+ "\n";
			}
			content=content+"\n查询更多影厅代码,请回复\"影厅代码\"";
			break;
		case 12:
			content = "上海国际电影节从6月14日开始,6月22日中午结束。在35家影院展出。";
			break;
		case 14:
			content = "订票请登录一票通官方网站:www.tickets.com.cn";
			break;
		case 13:
			content = "场次时间:\n" 
					+ "第一场开始时间:  9:00\n" + "第二场开始时间: 10:00\n" + "第三场开始时间: 13:30\n"
					+ "第四场开始时间: 15:45\n" + "第五场开始时间: 18:30\n"
					+ "第六场开始时间: 20:45\n" + "第七场开始时间: 23:00\n";
			break;
		case 21:
			return new weChat.send.NewsMsg(menuMsg.getFromUserName(),menuMsg.getToUserName(),
					new Date().getTime()+"",BaseMsg.MSG_TYPE_NEWS,"“神女”归来：当阮玲玉遇上交响乐","中国经典默片"
							+ "《神女》数字修复版将重现上海滩，于6月15日作为上海国际电影节的正式展映环节放映。一票通特别推出《神女》电"
							+ "影票，经典默片与美妙交响乐的完美结合。欢迎广大影迷致电、上网、上门订购！",
							"http://image.tickets.com.cn/day_140607/201406070111447647.jpg",""
							+ "http://siff.tickets.com.cn/news/detail_4958.html").getNewsMsg();
		case 31:
		/*	create(menuMsg.getFromUserName());
			File f = new File(this.getClass().getClassLoader().getResource("/")
					.getPath().replace("%20", " ")
					+ "aa.png");*/
			content="您的优惠码为:"+menuMsg.getFromUserName().substring(0,8)+"\n";
			content=content+"凭此码到一票通门店购买电影节门票，享受每张票优惠5元，最多兑换4张。\n";
			content=content+"该活动最终解释权归一票通所有。\n";
					

		}
		resultMsg = new weChat.send.TextMsg(menuMsg.getFromUserName(),
				menuMsg.getToUserName(), new Date().getTime() + "",
				BaseMsg.MSG_TYPE_TEXT, content).getTextMsg();
		return resultMsg;
	}

	/*生成二维码
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
				// 生成二维码
				BitMatrix matrix = writer.encode(userName,
						BarcodeFormat.QR_CODE, 200, 200);
				MatrixToImageWriter.writeToFile(matrix, "png", file);
				// 读取二维码

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else
			return true;*/

}
