package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.dao.ItemMapper;
import menghuanxianjing.mhxj.dao.UserMapper;
import menghuanxianjing.mhxj.model.UserModel;

@Service("ItemService")
public class ItemService {
	@Autowired
	ItemMapper itemMapper;
	
	public void insertItems(int sid,String name) {
		itemMapper.insertIntoItems(sid, name);
	}
	
	public boolean isExitedItem(int sid) {
		long itemCount=itemMapper.isExitedItem(sid);
		return itemCount>=1;
	}
	
	
	public String getNameBySid(int sid) {
		return itemMapper.getNameBySid(sid);
	}

}
