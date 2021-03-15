package menghuanxianjing.mhxj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.dao.ActionMapper;
import menghuanxianjing.mhxj.pojo.Action;

@Service
public class ActionService {
	@Autowired
	ActionMapper actionMapper;
	public void add(Action action) {
		actionMapper.add(action);
	}

}
