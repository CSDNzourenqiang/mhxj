package menghuanxianjing.mhxj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import menghuanxianjing.mhxj.pojo.Action;

@Mapper
public interface ActionMapper {
	
	@Insert(value = {"insert into oper_log(account,actionIp,content,actionDes) values(#{account},#{actionIp},#{content},#{actionDes})"} )
	
	public void add(Action action);

}
