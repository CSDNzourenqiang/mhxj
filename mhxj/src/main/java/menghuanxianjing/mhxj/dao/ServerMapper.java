package menghuanxianjing.mhxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.model.UserModel;

@Mapper
public interface ServerMapper {


	@Select(value = { "select * from server" })
	public List<Server> findAllServer();
	
	@Select(value = {"select * from server s where s.key=#{key} limit 1"})
	public Server findServerByKey(@Param("key") String key);
	
}
