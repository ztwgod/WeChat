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
		String appId = "wx85d0f6270789c95c";
		// 第三方用户唯一凭证密钥
		String appSecret = "7717311670e5e34854ddba2e90af5ea0";

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
		btn11.setName("影厅代码查询");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn12.setName("电影节日期查询");
		btn12.setType("click");
		btn12.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn13.setName("场次时间查询");
		btn13.setType("click");
		btn13.setKey("13");
		CommonButton btn14 = new CommonButton();
		btn14.setName("订票查询");
		btn14.setType("click");
		btn14.setKey("14");
		CommonButton btn21 = new CommonButton();
		btn21.setName("热门推荐");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn31 = new CommonButton();
		btn31.setName("领取优惠码");
		btn31.setType("click");
		btn31.setKey("31");
		

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("查询");
		mainBtn1.setsub_button(new CommonButton[] { btn11, btn12, btn13, btn14});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("热门推荐");
		mainBtn2.setsub_button(new CommonButton[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("优惠");
		mainBtn3.setsub_button(new CommonButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}