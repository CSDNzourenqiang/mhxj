package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("pay")
public class PayEntity {
	@Id
	@Field
	private String _id;
	private String orderid;
	private double amount;
	private String demi_channel;
	private String product_key;
	private String orderid_ch;

	public String getOrderid_ch() {
		return orderid_ch;
	}

	public void setOrderid_ch(String orderid_ch) {
		this.orderid_ch = orderid_ch;
	}

	private int pid;
	
	
	public PayEntity() {
		
	}
	
	public PayEntity(int id,String orderid,int pid,double amount) {
		
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getProduct_key() {
		return product_key;
	}

	public void setProduct_key(String product_key) {
		this.product_key = product_key;
	}

	public String getDemi_channel() {
		return demi_channel;
	}

	public void setDemi_channel(String demi_channel) {
		this.demi_channel = demi_channel;
	}
	
}
