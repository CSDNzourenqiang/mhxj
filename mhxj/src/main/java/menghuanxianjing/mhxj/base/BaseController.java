package menghuanxianjing.mhxj.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.service.ServerListService;

public class BaseController {
	
	@Autowired
	ServerListService serverListService;
	
	
	protected List<Server> getServers(){
		List<Server> area_list=serverListService.getAllServer();
		for(Server server:area_list) {
			server.setName(server.getServer_name());
		}
		return area_list;
	}

}
