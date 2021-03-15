package menghuanxianjing.mhxj.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document("shop")
public class LogShop {
	@Id
	@Field
	private String _id;
	private String source_type;
	private String target_val;
	private String source_val;
	private String subtype;
	private String target_type;
	private String reason;
	private String amount;
	private String item;
	private String item_id;
	private String cost;
	private String coin_type;
	private String shop_id;
	private String _time;
	private int pid;
	
	
	public LogShop() {
		
	}
	
	public LogShop(int id,String name,int pid,String account) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCoin_type() {
		return coin_type;
	}

	public void setCoin_type(String coin_type) {
		this.coin_type = coin_type;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	
	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getTarget_val() {
		return target_val;
	}

	public void setTarget_val(String target_val) {
		this.target_val = target_val;
	}

	public String getSource_val() {
		return source_val;
	}

	public void setSource_val(String source_val) {
		this.source_val = source_val;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getTarget_type() {
		return target_type;
	}

	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}

	public String get_time() {
		return _time;
	}

	public void set_time(String _time) {
		this._time = _time;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
