package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("mail")
public class MailInfo {
	@Id
	@Field
	private String _id;
	private String mailid;
	private String mail_title;
	private String attach;
	private String subtype;
	private String mail_time;
	private String keep_time;
	private String reason;
	
	
	
	public MailInfo() {
		
	}
	
	public MailInfo(int id,String name,int pid,String account) {
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getMail_time() {
		return mail_time;
	}

	public void setMail_time(String mail_time) {
		this.mail_time = mail_time;
	}

	public String getKeep_time() {
		return keep_time;
	}

	public void setKeep_time(String keep_time) {
		this.keep_time = keep_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
