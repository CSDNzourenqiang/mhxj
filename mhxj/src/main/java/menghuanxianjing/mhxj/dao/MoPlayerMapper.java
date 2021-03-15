package menghuanxianjing.mhxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.Query;

import menghuanxianjing.mhxj.pojo.Player;

@Mapper
public interface MoPlayerMapper {
	
	@Query("{}")
	public Page<Player> findAll();
}
