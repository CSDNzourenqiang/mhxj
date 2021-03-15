package menghuanxianjing.mhxj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javafx.geometry.Side;

@Mapper
public interface ItemMapper {
	
	@Insert("insert into items (sid,name) values(#{sid},#{name})")
	public void insertIntoItems(@Param("sid") int id,@Param("name") String name);
	
	@Select("select count(*) from items where sid=#{sid}")
	public long isExitedItem(@Param("sid") int sid);
	
	
	@Select("select name from items where sid=#{sid}")
	public String getNameBySid(@Param("sid") int sid);
}
