package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.dao.ServerMapper;
import menghuanxianjing.mhxj.dao.UserMapper;
import menghuanxianjing.mhxj.model.Server;

@Service("ServerListService")
public class ServerListService {
	@Autowired
	ServerMapper serverMapper;
	
	public List<Server> getAllServer(){
		return serverMapper.findAllServer();
	}
	
	public Server findServerByKey(String key) {
		return serverMapper.findServerByKey(key);
	}
	

}
