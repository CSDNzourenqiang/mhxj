package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.dao.RechargeMapper;
import menghuanxianjing.mhxj.model.RechargeItemsModel;

@Service
public class RechargeService {
	@Autowired RechargeMapper rechargeMapper;
	
	
	public List<RechargeItemsModel> getAllRechargeItems(){
		return rechargeMapper.findAllRechargeItems();
	}
	
	public void updateRechargeItems(Double amount,String product_key) {
		rechargeMapper.updateRechargeItems(amount,product_key);
	}
	
	public void clearAmount() {
		rechargeMapper.clearAmount();
	}
}
