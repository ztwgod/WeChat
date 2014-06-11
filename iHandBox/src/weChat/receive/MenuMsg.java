package weChat.receive;

public class MenuMsg extends EventMsg{
	public MenuMsg(String toUserName, String fromUserName, String createTime,
			String msgType, String event, String eventKey) {
		super(toUserName, fromUserName, createTime, msgType, event);
		setEventKey(eventKey);
		// TODO Auto-generated constructor stub
	}

	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	

}
