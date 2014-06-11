package weChat;

public class BaseMsg {
	public final static String MSG_TYPE_TEXT="text";
	public final static String MSG_TYPE_IMAGE="image";
	public final static String MSG_TYPE_VOICE="voice";
	public final static String MSG_TYPE_VIDEO="video";
	public final static String MSG_TYPE_LOCATION="location";
	public final static String MSG_TYPE_LINK="link";
	public final static String MSG_TYPE_MUSCI="music";
	public final static String MSG_TYPE_NEWS="news";
	public final static String MSG_TYPE_EVENT="event";
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
}
