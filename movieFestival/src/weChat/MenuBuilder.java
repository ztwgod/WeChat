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
		String appId = "wx85d0f6270789c95c";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "7717311670e5e34854ddba2e90af5ea0";

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
		btn11.setName("Ӱ�������ѯ");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn12.setName("��Ӱ�����ڲ�ѯ");
		btn12.setType("click");
		btn12.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn13.setName("����ʱ���ѯ");
		btn13.setType("click");
		btn13.setKey("13");
		CommonButton btn14 = new CommonButton();
		btn14.setName("��Ʊ��ѯ");
		btn14.setType("click");
		btn14.setKey("14");
		CommonButton btn21 = new CommonButton();
		btn21.setName("�����Ƽ�");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn31 = new CommonButton();
		btn31.setName("��ȡ�Ż���");
		btn31.setType("click");
		btn31.setKey("31");
		

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��ѯ");
		mainBtn1.setsub_button(new CommonButton[] { btn11, btn12, btn13, btn14});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("�����Ƽ�");
		mainBtn2.setsub_button(new CommonButton[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("�Ż�");
		mainBtn3.setsub_button(new CommonButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}