package weChat.pojo;

public class ComplexButton extends Button{
	private CommonButton[] sub_button;
	public CommonButton[] getsub_button() {
		return sub_button;
	}

	public void setsub_button(CommonButton[] subButton) {
		this.sub_button = subButton;
	}

	
	

}
