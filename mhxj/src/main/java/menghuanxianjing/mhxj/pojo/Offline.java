package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("offline")
public class Offline {
	@Id
	@Field
	private String _id;
	private String mail_info;
	private String profile_info;
	private String partner_info;
	private int pid;
	
	
	public Offline() {
		
	}
	
	public Offline(int id,String name,int pid,String account) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getMail_info() {
		return mail_info;
	}

	public void setMail_info(String mail_info) {
		this.mail_info = mail_info;
	}

	public String getProfile_info() {
		return profile_info;
	}

	public void setProfile_info(String profile_info) {
		this.profile_info = profile_info;
	}

	public String getPartner_info() {
		return partner_info;
	}

	public void setPartner_info(String partner_info) {
		this.partner_info = partner_info;
	}
	
}
