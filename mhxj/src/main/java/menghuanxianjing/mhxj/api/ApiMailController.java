package menghuanxianjing.mhxj.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import menghuanxianjing.mhxj.pojo.PayEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menghuanxianjing.mhxj.annotation.SystemControllerLog;
import menghuanxianjing.mhxj.model.MailModel;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.pojo.Player;
import menghuanxianjing.mhxj.pojo.PlayerInfo;
import menghuanxianjing.mhxj.service.PlayerServiceImpl;
import menghuanxianjing.mhxj.service.ServerListService;
import menghuanxianjing.utils.Coin_flag;
import menghuanxianjing.utils.Constants;
import menghuanxianjing.utils.HttpUtils;
import net.sf.json.JSONObject;

import javax.swing.text.html.parser.Entity;

@RestController
public class ApiMailController {
	
	@Value("${server.bs_ip}")
	private String ip;

	@Value("${server.lianxu_startday}")
	private int lianxu_startday;
	@Value("${server.lianxu_endday}")
	private int lianxu_endday;
	@Value("${server.leichong_startday}")
	private int leichong_startday;
	@Value("${server.leichong_endday}")
	private int leichong_endday;
	@Autowired
	PlayerServiceImpl playerService;
	
	@Autowired
	ServerListService serverListService;

	
	
	@RequestMapping(value = "/api/sendMail")
	@SystemControllerLog(description = "发送邮件调用")
	public String sendMail(MailModel mail) throws Exception {
		MailModel mailModel=mail;
		String body ="";
		String[] items=mailModel.getItems().split(";",-1);
        String mail_content="[";
        String money="[";//虚拟货币
        for(String item:items){
        	String sid=item.split(":")[0].split("_")[0];
        	String cnt=item.split(":")[1];
        	if (Integer.parseInt(sid)>=1001&&Integer.parseInt(sid)<=1028) {
        		Coin_flag coin_flag=Coin_flag.getCoinId(sid);
        		if (coin_flag!=null) {
        			money=money+"{\"sid\":"+coin_flag.getId()+",\"value\":"+cnt+"},";
				}
        		
			}else {
            	mail_content=mail_content+"{\"sid\":"+sid+",\"cnt\":"+cnt+"},";

			}
        }
        System.out.println(mail_content);
        if (!mail_content.equals("[")) {
        	mail_content=mail_content.substring(0,mail_content.length()-1)+"]";
		}else {
			mail_content=mail_content+"]";
		}
        if (!money.equals("[")) {
        	money=money.substring(0,money.length()-1)+"]";
		}else {
			money=money+"]";
		}
        //mailtype=all
        String area=mailModel.getArea();
        Server server=serverListService.findServerByKey(area);
        if (server==null) {
			return "";
		}
        final String contentString=mail_content;
        final String moneyString=money;
        if (mailModel.getMail_type().equals("all")) {
        	
    		int status=0;
    		new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<PlayerInfo> list=new ArrayList<PlayerInfo>();
		    		if (area.contains("gs10001")) {
		    			try {
							list=playerService.getAllPlayerInfos(server);
						} catch (Exception e) {
							e.printStackTrace();
						}
		    		}
		    		
					for(PlayerInfo player:list) {
						JSONObject base_info=JSONObject.fromObject(player.getBase());
						if (System.currentTimeMillis()/1000-base_info.getInt("offlinetime")<3*24*3600) {
							String body= "{\"module\":\"report\",\"cmd\":\"SendMailGM\",\"args\":{\"theme\":\""+mailModel.getTheme()+"\",\"content\":\""+mailModel.getContent()+"\",\"serverkey\":\""+mailModel.getArea()+"\",\"mail_type\":\"person\",\"key\":\""+player.getPid()+"\",\"value\":12,\"items\":"+contentString+",\"money\":"+moneyString+"}}";
			    		    System.out.println(body);
			    	        //money有可能需要单独处理
			    	        try {
								HttpUtils.POST(ip, "/backend/", body);
							} catch (ClientProtocolException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
			    		    try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
		    			
		    		}
				}
			}).start();
    		status=200;
    		if (status==200) {
		    	return "发送时间"+Constants.SDF.format(new Date())+":发送邮件成功";
			}else {
				return "发送时间"+Constants.SDF.format(new Date())+"发送失败";
			}
		}else {
			body= "{\"module\":\"report\",\"cmd\":\"SendMailGM\",\"args\":{\"theme\":\""+mailModel.getTheme()+"\",\"content\":\""+mailModel.getContent()+"\",\"serverkey\":\""+mailModel.getArea()+"\",\"mail_type\":\""+mailModel.getMail_type()+"\",\"key\":\""+mailModel.getToPlayer()+"\",\"value\":12,\"items\":"+mail_content+",\"money\":"+money+"}}";
		    System.out.println(body);
	        //money有可能需要单独处理
	    	//{{sid=gamedefines.COIN_FLAG.COIN_GOLD, value = 30000},{sid=gamedefines.COIN_FLAG.COIN_COIN, value = 500000},}
	    	//{{sid=14004,cnt=200},{sid=13216,cnt=1},{sid=13217,cnt=1}}
	        int status=HttpUtils.POST(ip, "/backend/", body);
		    if (status==200) {
		    	return "发送时间"+Constants.SDF.format(new Date())+":发送邮件成功";
			}else {
				return "发送时间"+Constants.SDF.format(new Date())+"发送失败";
			}
		}
	}

	@Scheduled(cron = "0 0 0 * * ? ")
	public void 每日返利() throws Exception {
		List<Server> allServer = serverListService.getAllServer();
		Map<Integer,Double> map=new HashMap<>();
		for(Server server:allServer){
			List<PayEntity> allbills = playerService.getAllbills(server);
			for(PayEntity payEntity:allbills){
				String orderid_ch = payEntity.getOrderid_ch();
				String substring = orderid_ch.substring(3, 11);
				if(!substring.equals(获取前一天日期())){
					allbills.remove(payEntity);
				}else {
					if(map.containsKey(payEntity.getPid())){
						double o = (double)map.get(payEntity.getPid());
						map.put(payEntity.getPid(),o+payEntity.getAmount());
					}else {
						map.put(payEntity.getPid(),payEntity.getAmount());
					}
				}
			}
			for(Map.Entry<Integer,Double> entity:map.entrySet()){
				String pid = String.valueOf(entity.getKey());
				Double money = entity.getValue();
				if(money<100){
					continue;
				}
				if(money>=100){
					发送每日返利(100,"",pid,server);
				}
				if(money>=300){
					发送每日返利(300,"",pid,server);
				}
				if(money>=500){
					发送每日返利(500,"",pid,server);
				}
				if(money>=1000){
					发送每日返利(1000,"",pid,server);
				}
				if(money>=2000){
					发送每日返利(2000,"",pid,server);
				}
				if(money>=5000){
					发送每日返利(5000,"",pid,server);
				}
				if(money>=10000){
					发送每日返利(10000,"",pid,server);
				}
			}
			map.clear();
		}
	}
	@Scheduled(cron = "0 0 0 * * ? ")
	public void 连续返利() throws Exception {
		String beforetime = 获取前一天日期();
		int nowtime = Integer.valueOf(beforetime) + 1;
		if(nowtime>lianxu_endday||nowtime<lianxu_startday){//如果现在的时间不在活动时间内
			return;
		}
		List<Server> allServer = serverListService.getAllServer();
		Map<String,Double> map=new HashMap<>();
		Map<Integer,Integer> map1=new HashMap<>();
		for(Server server:allServer){
			List<PayEntity> allbills = playerService.getAllbills(server);
			for(PayEntity payEntity:allbills){
				String orderid_ch = payEntity.getOrderid_ch();
				String substring = orderid_ch.substring(3, 11);
				Integer orderTime = Integer.getInteger(substring);
				if(orderTime>lianxu_endday||orderTime<lianxu_startday){//如果订单不在活动时间内
					allbills.remove(payEntity);
				}else {
					String pidAndTime=payEntity.getPid()+substring;
					if(map.containsKey(pidAndTime)){//如果玩家是同一天的重复订单
						allbills.remove(payEntity);
					}else {
						map.put(pidAndTime,payEntity.getAmount());
					}
				}
				if(!map.containsKey(payEntity.getPid())){
					map1.put(payEntity.getPid(),1);
				}else {
					map1.put(payEntity.getPid(),map1.get(payEntity.getPid())+1);
				}
			}
			for(Map.Entry<Integer,Integer> entry:map1.entrySet()){
				String pid = String.valueOf(entry.getKey());
				Integer day = entry.getValue();
				switch (day){
					case 1:
						发送连续返利(day,"1",pid,server);
						break;
					case 2:
						发送连续返利(day,"2",pid,server);
						break;
					case 3:
						发送连续返利(day,"3",pid,server);
						break;
					case 4:
						发送连续返利(day,"4",pid,server);
						break;
					case 5:
						发送连续返利(day,"5",pid,server);
						break;
					case 6:
						发送连续返利(day,"6",pid,server);
						break;
					case 7:
						发送连续返利(day,"7",pid,server);
						break;
				}
			}
			map.clear();
			map1.clear();
		}
	}
	@Scheduled(cron = "0 0 0 * * ? ")
	public void 累充返利() throws Exception {
		List<Server> allServer = serverListService.getAllServer();
		Map<Integer,Double> map=new HashMap<>();
		for(Server server:allServer){
			List<PayEntity> allbills = playerService.getAllbills(server);
			for(PayEntity payEntity:allbills){
				String orderid_ch = payEntity.getOrderid_ch();
				String substring = orderid_ch.substring(3, 11);
				Integer orderTime = Integer.getInteger(substring);
				if(orderTime>leichong_endday||orderTime<leichong_startday){
					allbills.remove(payEntity);
				}else {
					if(map.containsKey(payEntity.getPid())){
						double o = (double)map.get(payEntity.getPid());
						map.put(payEntity.getPid(),o+payEntity.getAmount());
					}else {
						map.put(payEntity.getPid(),payEntity.getAmount());
					}
				}
			}
			for(Map.Entry<Integer,Double> entity:map.entrySet()){
				String pid = String.valueOf(entity.getKey());
				Double money = entity.getValue();
				if(money<100){
					continue;
				}
				if(money>=100){
					发送累充返利(100,"",pid,server);
				}
				if(money>=300){
					发送累充返利(300,"",pid,server);
				}
				if(money>=500){
					发送累充返利(500,"",pid,server);
				}
				if(money>=1000){
					发送累充返利(1000,"",pid,server);
				}
				if(money>=2000){
					发送累充返利(2000,"",pid,server);
				}
				if(money>=5000){
					发送累充返利(5000,"",pid,server);
				}
				if(money>=10000){
					发送累充返利(10000,"",pid,server);
				}
			}
			map.clear();
		}
	}
	public void 发送每日返利(int money,String item,String pid,Server server) throws Exception {
		MailModel mailModel=new MailModel("每日返利"+money+"元邮件","每日返利"+money+"元邮件",pid,"1002_金币:10",null,"person",server.getKey());
		sendMail(mailModel);
	}
	public void 发送连续返利(int day,String item,String pid,Server server) throws Exception {
		MailModel mailModel=new MailModel("；连续返利"+day+"天邮件","连续返利"+day+"天邮件",pid,"1002_金币:10",null,"person",server.getKey());
		sendMail(mailModel);
	}
	public void 发送累充返利(int money,String item,String pid,Server server) throws Exception {
		MailModel mailModel=new MailModel("；累充返利"+money+"元邮件","累充返利"+money+"元邮件",pid,"1002_金币:10",null,"person",server.getKey());
		sendMail(mailModel);
	}
	public String 获取前一天日期(){
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		String month= String.valueOf(cal.get(Calendar.MONTH) + 1);
		if(month.length()==1){
			month = "0"+month;
		}
		String day=String.valueOf(cal.get(Calendar.DATE)-1);
		if(day.length()==1){
			day = "0"+day;
		}
		return year+month+day;
	}

}
