package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("playerinfo")
public class PlayerInfo {
	@Id
	@Field
	private String _id;
	private String base;
	private int pid;
	
	
	public PlayerInfo() {
		
	}
	
	public PlayerInfo(int id,String name,int pid,String account) {
		
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

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
}
