package weChat;

import weChat.pojo.AccessToken;
import weChat.pojo.Button;
import weChat.pojo.CommonButton;
import weChat.pojo.ComplexButton;
import weChat.pojo.Menu;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuBuilder {
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx6e51edf42aee0529";
		// 第三方用户唯一凭证密钥
		String appSecret = "dd270e0890de904d124b4846827f0748";

		// 调用接口获取access_token
		AccessToken at = httpsUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = httpsUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				System.out.print("菜单创建成功!");
			else
				System.out.print("菜单创建失败!");
		}
		/* test sim
		try {
			System.out.println(Sim.simTalk("我饿了"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("物箱查询");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn12.setName("订单查询");
		btn12.setType("click");
		btn12.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn13.setName("远程开箱");
		btn13.setType("view");
		btn13.setUrl("http://425.myxbwechat.sinaapp.com/WeChat/openBox.jsp");
		CommonButton btn21 = new CommonButton();
		btn21.setName("校园生活");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn31 = new CommonButton();
		btn31.setName("娱乐一下");
		btn31.setType("click");
		btn31.setKey("31");
		

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("业务操作");
		mainBtn1.setsub_button(new CommonButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("校园生活");
		mainBtn2.setsub_button(new CommonButton[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setsub_button(new CommonButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}