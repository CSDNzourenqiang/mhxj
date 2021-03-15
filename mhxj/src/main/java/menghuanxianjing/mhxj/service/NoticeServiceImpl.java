package menghuanxianjing.mhxj.service;

import java.util.List;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.pojo.NoticeEntity;
import menghuanxianjing.mhxj.repository.PlayerRepository;
@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	PlayerRepository playerRepository;

	@Override
	public List<NoticeEntity> getAllNotice() {
		return playerRepository.getAllNotice();
	}
	


}
