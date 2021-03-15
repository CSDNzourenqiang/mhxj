package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.pojo.NoticeEntity;


@Service
public interface NoticeService {
	
	/**
	 * 获取当前的公告信息
	 */
	public List<NoticeEntity> getAllNotice();

}
