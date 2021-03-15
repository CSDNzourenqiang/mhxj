package menghuanxianjing.mhxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import menghuanxianjing.mhxj.model.RechargeItemsModel;

@Mapper
public interface RechargeMapper {

	
	@Select("select * from recharge")
	public List<RechargeItemsModel> findAllRechargeItems();
	
	@Update("update recharge r set r.amount=r.amount+#{amount},r.pay_times=r.pay_times+1 where r.key=#{key1}")
	public void updateRechargeItems(@Param("amount") Double amount,@Param("key1") String key);
	
	@Update("update recharge set amount=0,pay_times=0")
	public void clearAmount();
}
