package menghuanxianjing.mhxj.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menghuanxianjing.mhxj.model.CDKModel;
import menghuanxianjing.mhxj.model.MailModel;
import menghuanxianjing.utils.Constants;
import menghuanxianjing.utils.HttpUtils;

@RestController
public class ApiCDKController {
	
	@Value("${server.bs_ip}")
	private String ip;
	
	
	/**
	 * 生成礼包码
	 * @param cdkModel
	 * @return
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/createCdk")
	public String sendMail(CDKModel cdkModel) throws ClientProtocolException, URISyntaxException, IOException {
		long create_time=System.currentTimeMillis()/1000;
		long expire_day=cdkModel.getExpire_days();
		long expire_time=create_time+expire_day*24*3600;
		long amount=cdkModel.getAmount();
		String body = "{\"redeem_id\":\""+cdkModel.getRedeem_id()+"\",\"module\":\"redeemcode\",\"cmd\":\"MakeRedeemCode\",\"data\":{\"redeem_id\":"+cdkModel.getRedeem_id()+",\"amount\":"+amount+",\"operater\":\"\",\"publisher\":\"dd\",\"redeem_name\":\""+cdkModel.getRedeem_name()+"\",\"create_time\":"+create_time+",\"expire_time\":"+expire_time+",\"gift_id\":"+cdkModel.getGift_id()+",\"player_redeem_cnt\":1,\"code_redeem_cnt\":"+cdkModel.getCode_redeem_cnt()+"},\"args\":{}}";

        System.out.println(body);
        int status=HttpUtils.POST(ip, "/redeemcode/Forward", body);
        if (status==200) {
			return Constants.SDF.format(new Date())+"生成成功";
		}else {
			return Constants.SDF.format(new Date())+"生成失败";
		}
	}
	
	@RequestMapping("/api_test.php")
	public String test() {
		return "";
	}
	
	
	

}
