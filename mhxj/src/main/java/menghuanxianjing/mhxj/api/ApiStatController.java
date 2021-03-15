package menghuanxianjing.mhxj.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menghuanxianjing.mhxj.model.RechargeItemsModel;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.pojo.ItemEntity;
import menghuanxianjing.mhxj.pojo.PayEntity;
import menghuanxianjing.mhxj.service.ItemService;
import menghuanxianjing.mhxj.service.PlayerServiceImpl;
import menghuanxianjing.mhxj.service.RechargeService;
import menghuanxianjing.mhxj.service.ServerListService;
import menghuanxianjing.utils.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class ApiStatController {
	
	@Value("${server.bs_ip}")
	private String ip;
	
	@Autowired
	PlayerServiceImpl playerService;
	
	@Autowired
	RechargeService rechargeService;
	
	@Autowired
	ServerListService serverListService;
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value = "/api/stat")	
	public JSONObject searchStat(int ipid,String area,String name) throws Exception {
		Server server=serverListService.findServerByKey(area);
		if (server==null) {
			return null;
		}
		List<PayEntity> bills=playerService.getAllbills(server);
		rechargeService.clearAmount();		
		JSONObject jsonObject=new JSONObject();
		double amount=0;
		if (bills!=null) {
			for (PayEntity payEntity:bills) {
				
				if (payEntity==null) {
					continue;
				}
				if (payEntity.getDemi_channel().equals("0")) {
					continue;
				}
				amount+=payEntity.getAmount();
				rechargeService.updateRechargeItems(payEntity.getAmount(),payEntity.getProduct_key());
			}
		}
		List<RechargeItemsModel> rechargeList=rechargeService.getAllRechargeItems();
		String d_1="";
		String d_2="";
		String d_3="";
		for(RechargeItemsModel rechargeItemsModel:rechargeList) {
			
			if (d_1.equals("")) {
				d_1="\\\""+rechargeItemsModel.getName()+"\\\"";
			}else {
				d_1=d_1+",\\\""+rechargeItemsModel.getName()+"\\\"";
			}
			
			if (d_2.equals("")) {
				d_2=rechargeItemsModel.getAmount()/100+"";
			}else {
				d_2=d_2+","+rechargeItemsModel.getAmount()/100;
			}
			
			if (d_3.equals("")) {
				d_3=rechargeItemsModel.getPay_times()+"";
			}else {
				d_3=d_3+","+rechargeItemsModel.getPay_times();
			}
		}
		jsonObject.accumulate("payAmount", amount/100);
		String option="{\"title\":{\"text\":\"充值选项分布\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"data\":\"['购买额','购买次数']\"},\"grid\":{\"left\":\"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\":true},\"xAxis\":{},\"yAxis\":{\"data\":\"["+d_1+"]\",\"type\":\"category\"},\"series\":[{\"name\":\"购买额\",\"type\":\"bar\",\"data\":\"["+d_2+"]\"},{\"name\":\"购买次数\",\"type\":\"bar\",\"data\":\"["+d_3+"]\"}]}";
		JSONObject json_options=JSONObject.fromObject(option);
		jsonObject.accumulate("bills", JSONArray.fromObject(bills));
		jsonObject.accumulate("areaName", area);
		jsonObject.accumulate("options", json_options);
	    return jsonObject;
	}
	@RequestMapping("/api/statitem")
	public JSONObject  searchStatItem(String area) throws Exception {
		Server server=serverListService.findServerByKey(area);
		if (server==null) {
			return null;
		}
		List<ItemEntity> items=playerService.findItems(server);
		Map<String, Integer> item_map=new HashMap<String, Integer>();
		for(ItemEntity itemEntity:items) {
			String item_name=itemService.getNameBySid(itemEntity.getSid());
			if (item_map.containsKey(item_name)) {
				item_map.put(item_name, itemEntity.getAmount()+item_map.get(item_name));
			}else {
				item_map.put(item_name, itemEntity.getAmount());
			}
			
		}
		JSONObject jsonObject=new JSONObject();
		String d_1="";
		String d_2="";
		for(Map.Entry<String, Integer> entry:item_map.entrySet()) {
			String mapKey=entry.getKey();
			Integer mapValue=entry.getValue();
			if (d_1.equals("")) {
				d_1="\\\""+mapKey+"\\\"";
			}else {
				d_1=d_1+",\\\""+mapKey+"\\\"";
			}
			
			if (d_2.equals("")) {
				d_2=mapValue+"";
			}else {
				d_2=d_2+","+mapValue;
			}
		}
		
		String option="{\"title\":{\"text\":\"商城购买物品统计\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"data\":\"['总购买数量']\"},\"grid\":{\"left\":\"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\":true},\"xAxis\":{},\"yAxis\":{\"data\":\"["+d_1+"]\",\"type\":\"category\"},\"series\":[{\"name\":\"购买额\",\"type\":\"bar\",\"data\":\"["+d_2+"]\"}]}";
		JSONObject json_options=JSONObject.fromObject(option);
		jsonObject.accumulate("areaName", area);
		jsonObject.accumulate("options", json_options);
	    return jsonObject;
	}
	
	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {
		//String body = "{\"operatorid\":\"0\",\"cmd\":\"10201\",\"type\":\"json\",\"sign\":\"123123\",\"req\":{\"zid\":1,\"name\":[\"公孙方\"],\"title\":\"测试\",\"content\":\"测试\",\"items\":[{\"name\":\"主角经验礼包\",\"tid\":2000916,\"itemNum\":1000}]}}";
		//String body = "{\"operatorid\":\"0\",\"cmd\":\"10102\",\"type\":\"json\",\"sign\":\"123123\",\"req\":{\"rollid\":2,\"playInterval\":1,\"playCount\":99,\"beginTime\":1581758867,\"content\":\"安抚打扫了房间阿里山的肌肤上的纠纷\"}}";
		//String body="{\"nt_data\":\"@108@119@174@165@158@82@167@152@171@166@161@162@159@115@90@106@94@103@83@85@154@166@156@161@155@160@166@155@118@90@139@141@118@101@110@90@82@165@165@148@167@151@153@159@160@164@157@118@82@165@160@87@116@118@117@165@162@176@165@163@168@166@169@152@157@157@169@171@147@153@150@113@117@160@157@166@164@151@159@158@110@115@166@158@153@118@109@110@102@172@161@152@119@116@162@168@151@161@164@151@160@147@158@152@119@164@169@164@162@167@169@117@95@163@160@156@158@166@152@160@152@164@157@114@117@167@171@173@143@167@168@156@151@164@144@161@168@113@116@98@160@171@172@152@159@169@149@154@167@151@167@161@117@115@167@166@157@157@168@152@158@167@116@104@99@98@97@101@105@100@108@100@98@102@109@106@101@106@100@101@102@105@106@100@111@103@107@103@117@103@165@171@148@157@168@151@160@161@111@111@169@148@177@146@165@159@165@158@110@105@97@102@105@101@106@99@100@103@109@84@106@109@112@108@99@114@103@108@110@97@161@148@178@146@172@156@158@155@118@117@145@164@160@170@163@172@119@98@101@103@105@112@104@153@163@168@165@166@170@118@110@165@165@148@173@168@171@113@97@114@103@172@164@152@165@170@168@118@117@151@175@171@170@149@172@151@166@154@162@153@163@171@112@170@169@171@117@98@157@171@165@168@153@172@143@167@146@167@150@165@172@112@115@102@165@153@172@171@151@160@149@118@114@103@165@157@170@160@168@162@166@166@144@163@157@172@163@152@152@154@115\",\"sign\":\"@104@155@109@158@105@104@104@106@111@103@112@103@148@152@104@158@149@111@101@151@102@111@156@149@155@112@113@105@154@110@108@106\",\"md5Sign\":\"07dae8d71374651c1f0ee4cc16a37f6e\"}";
        String body="{\"operatorid\":\"0\",\"cmd\":\"10007\",\"type\":\"json\",\"sign\":\"123123\",\"req\":{\"name\":\"潇洒的闻人霜\",\"zid\":2,\"playCount\":99,\"beginTime\":1581758867,\"content\":\"安抚打扫了房间阿里山的肌肤上的纠纷\"}}";
		int status=HttpUtils.POST("127.0.0.1:9090", "/CS_QueryPlayer", body);
        
	}
}
