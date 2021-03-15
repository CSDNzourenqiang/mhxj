package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.dao.UserMapper;
import menghuanxianjing.mhxj.model.UserModel;

@Service("UserService")
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public boolean isValidToken(String token) {
		List<UserModel> list=userMapper.findByToken(token);
		
		return list.size()==1;
	}
	
	public void insertUser(UserModel userModel) {
		userMapper.insertUser(userModel);
	}
	public void updateUser(UserModel userModel) {
		userMapper.updateUser(userModel);
	}
	
	public UserModel findUserByPassword(UserModel userModel) {
		return userMapper.findByPassword(userModel);
	}
	

}
