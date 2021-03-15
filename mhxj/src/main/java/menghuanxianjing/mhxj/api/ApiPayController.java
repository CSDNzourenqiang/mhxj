package menghuanxianjing.mhxj.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menghuanxianjing.mhxj.annotation.SystemControllerLog;
import menghuanxianjing.mhxj.pojo.Action;
import menghuanxianjing.utils.Constants;
import menghuanxianjing.utils.HttpUtils;

@RestController
public class ApiPayController {
	
	@Value("${server.bs_ip}")
	private String ip;
	
	
	
	@RequestMapping(value = "/api/api_charge.php")
	@SystemControllerLog(description = "充值补单操作")
	public String sendMail(int ipid,String area,String cmd) throws ClientProtocolException, URISyntaxException, IOException {
		String body ="";
		//非空经验
        body= "{\"module\":\"report\",\"cmd\":\"AddOrder\",\"args\":{\"gm\":1,\"serverkey\":\""+area+"\",\"type\":\"gmmail\",\"ipid\":\""+ipid+"\",\"ival\":"+0+",\"cmd\":\""+cmd+"\"}}";
        System.out.println(body);
        int status=HttpUtils.POST(ip, "/backend/", body);
	    if (status==200) {
	    	return "发送时间"+Constants.SDF.format(new Date())+":充值补单成功";
		}else {
			return "发送时间"+Constants.SDF.format(new Date())+"补单失败";
		}

        
	}
	@RequestMapping(value = "/api/api_addtask.php")
	@SystemControllerLog(description = "添加任务")
	public String addTask(int ipid,String area,String cmd) throws ClientProtocolException, URISyntaxException, IOException {
		String body ="";
		//非空经验
        body= "{\"module\":\"report\",\"cmd\":\"AddOrder\",\"args\":{\"gm\":1,\"serverkey\":\""+area+"\",\"type\":\"gmmail\",\"ipid\":\""+ipid+"\",\"ival\":"+0+",\"cmd\":\""+cmd+"\"}}";
        System.out.println(body);
        int status=HttpUtils.POST(ip, "/backend/", body);
	    if (status==200) {
	    	return "发送时间"+Constants.SDF.format(new Date())+":添加任务成功";
		}else {
			return "发送时间"+Constants.SDF.format(new Date())+"添加任务失败";
		}

        
	}
	@RequestMapping(value = "/api/api_cleartask.php")
	@SystemControllerLog(description = "清除任务")
	public String clearTask(int ipid,String area,String cmd) throws ClientProtocolException, URISyntaxException, IOException {
		String body ="";
		//非空经验
        body= "{\"module\":\"report\",\"cmd\":\"AddOrder\",\"args\":{\"gm\":1,\"serverkey\":\""+area+"\",\"type\":\"gmmail\",\"ipid\":\""+ipid+"\",\"ival\":"+0+",\"cmd\":\""+cmd+"\"}}";
        System.out.println(body);
        int status=HttpUtils.POST(ip, "/backend/", body);
	    if (status==200) {
	    	return "发送时间"+Constants.SDF.format(new Date())+":清除任务成功";
		}else {
			return "发送时间"+Constants.SDF.format(new Date())+":清除任务失败";
		}

        
	}

}
