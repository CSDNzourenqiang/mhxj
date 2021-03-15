package menghuanxianjing.mhxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import menghuanxianjing.mhxj.model.UserModel;

@Mapper
public interface UserMapper {


	@Select(value = { "select * from user where token=#{token}" })
	public List<UserModel> findByToken(String token);
	
	@Select(value = { "select * from user where account=#{account} and pass=#{pass} limit 1" })
	public UserModel findByPassword(UserModel userModel);
	
	@Insert(value = { "insert into user(account,pass,token) values(#{account},#{pass},#{token})" })
	public void insertUser(UserModel userModel);
	
	@Insert(value = { "update user set token=#{token} where account=#{account} and pass=#{pass}" })
	public void updateUser(UserModel userModel);
	
}
