package weChat.receive;

import weChat.BaseMsg;

public class EventMsg extends BaseMsg{
	private String event;

	public EventMsg(String toUserName, String fromUserName, String createTime,
			String msgType, String event) {
		setToUserName(toUserName);
		setFromUserName(fromUserName);
		setMsgType(msgType);
		setCreateTime(createTime);
		setEvent(event);
		
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
