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
public class ApidGMOrderController {
	
	@Value("${server.bs_ip}")
	private String ip;
	
	
	
	@RequestMapping(value = "/api/sendExp")
	@SystemControllerLog(description = "玩家升级经验操作")
	public String sendMail(int ival,int ipid,String area) throws ClientProtocolException, URISyntaxException, IOException {
		String body ="";
		//非空经验
        body= "{\"module\":\"report\",\"cmd\":\"SendEXP\",\"args\":{\"gm\":1,\"serverkey\":\""+area+"\",\"type\":\"gmmail\",\"ipid\":\""+ipid+"\",\"ival\":"+ival+"}}";
        System.out.println(body);
        int status=HttpUtils.POST(ip, "/backend/", body);
	    if (status==200) {
	    	return "发送时间"+Constants.SDF.format(new Date())+":发送经验成功";
		}else {
			return "发送时间"+Constants.SDF.format(new Date())+"发送失败";
		}

        
	}

}
