package weChat.receive;

import weChat.BaseMsg;

public class TextMsg extends BaseMsg{
	
	private String content;
	public TextMsg(String toUserName,String fromUserName,String createTime,
				   String msgType,String content) {
		setToUserName(toUserName);
		setFromUserName(fromUserName);
		setMsgType(msgType);
		setCreateTime(createTime);
		setContent(content);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
