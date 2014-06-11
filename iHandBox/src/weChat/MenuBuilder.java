package weChat;

import weChat.pojo.AccessToken;
import weChat.pojo.Button;
import weChat.pojo.CommonButton;
import weChat.pojo.ComplexButton;
import weChat.pojo.Menu;

/**
 * �˵���������
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuBuilder {
	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wx6e51edf42aee0529";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "dd270e0890de904d124b4846827f0748";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = httpsUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// ���ýӿڴ����˵�
			int result = httpsUtil.createMenu(getMenu(), at.getToken());

			// �жϲ˵��������
			if (0 == result)
				System.out.print("�˵������ɹ�!");
			else
				System.out.print("�˵�����ʧ��!");
		}
		/* test sim
		try {
			System.out.println(Sim.simTalk("�Ҷ���"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("�����ѯ");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn12.setName("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn13.setName("Զ�̿���");
		btn13.setType("view");
		btn13.setUrl("http://425.myxbwechat.sinaapp.com/WeChat/openBox.jsp");
		CommonButton btn21 = new CommonButton();
		btn21.setName("У԰����");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn31 = new CommonButton();
		btn31.setName("����һ��");
		btn31.setType("click");
		btn31.setKey("31");
		

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("ҵ�����");
		mainBtn1.setsub_button(new CommonButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("У԰����");
		mainBtn2.setsub_button(new CommonButton[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.setsub_button(new CommonButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}