package menghuanxianjing.mhxj.pojo;

import java.util.Date;

public class Action {
	private long userId;
	private String account;
	private String content;
	private String actionType;
	private String actionIp;
	private Date actionTime;
	private String actionDes;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionIp() {
		return actionIp;
	}
	public void setActionIp(String actionIp) {
		this.actionIp = actionIp;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionDes() {
		return actionDes;
	}
	public void setActionDes(String actionDes) {
		this.actionDes = actionDes;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	
	
}
