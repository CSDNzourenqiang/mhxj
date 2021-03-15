package menghuanxianjing.mhxj.model;



public class MailModel {
	
	private String content;
	private String theme;
	private String toPlayer;
	private String items;
	private String exp;
	private String mail_type;

	public MailModel(String content, String theme, String toPlayer, String items, String exp, String mail_type, String area) {
		this.content = content;
		this.theme = theme;
		this.toPlayer = toPlayer;
		this.items = items;
		this.exp = exp;
		this.mail_type = mail_type;
		this.area = area;
	}

	private String area;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getToPlayer() {
		return toPlayer;
	}
	public void setToPlayer(String toPlayer) {
		this.toPlayer = toPlayer;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getMail_type() {
		return mail_type;
	}
	public void setMail_type(String mail_type) {
		this.mail_type = mail_type;
	}
	
	
	

}
