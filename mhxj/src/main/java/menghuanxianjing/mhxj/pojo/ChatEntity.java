package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("chat")
public class ChatEntity {
	@Id
	@Field
	private String _id;
	private String name;
	private String svr;
	private String text;
	private String _time;
	private int pid;
	
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSvr() {
		return svr;
	}

	public void setSvr(String svr) {
		this.svr = svr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String get_time() {
		return _time;
	}

	public void set_time(String _time) {
		this._time = _time;
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
	
}
