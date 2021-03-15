package menghuanxianjing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import menghuanxianjing.mhxj.service.ItemService;


@Component
public class AfterFinishedRunner implements ApplicationRunner{
	@Value("${server.win_path}")
	private String win_path;
	
	@Value("${server.linux_path}")
	private String linux_path;
	
	@Autowired
	ItemService itemService;
	
	private static final Logger LOG=LoggerFactory.getLogger(AfterFinishedRunner.class);
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		LOG.info(System.getProperties().getProperty("os.name"));
		if (Constants.itemString.equals("")) {
			String path="";
			if(System.getProperties().getProperty("os.name").contains("Windows")) {
				path=win_path;
			}else if(System.getProperties().getProperty("os.name").contains("Linux")) {
				path=linux_path;
			}else {
				
				LOG.warn("识别系统出错");
			}
			Constants.itemString=FileUtils.readFile(path);
			Constants.itemString=Constants.itemString.substring(0,Constants.itemString.length()-1);
			for(String item:Constants.itemString.split(",")) {
				if(item.split("_")[0].equals("")){
					continue;
				}
				int sid=Integer.parseInt(item.split("_")[0]);
				if (itemService.isExitedItem(sid)) {
					continue;
				}
				itemService.insertItems(sid, (item.split("_")[1]).replace("\"", ""));
			}
    	}
		
		
	}

}
