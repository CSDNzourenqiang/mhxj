package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.pojo.ChatEntity;
import menghuanxianjing.mhxj.pojo.ItemEntity;
import menghuanxianjing.mhxj.pojo.LogShop;
import menghuanxianjing.mhxj.pojo.MailInfo;
import menghuanxianjing.mhxj.pojo.Offline;
import menghuanxianjing.mhxj.pojo.PayEntity;
import menghuanxianjing.mhxj.pojo.Player;
import menghuanxianjing.mhxj.pojo.PlayerInfo;


@Service
public interface PlayerService {
	
	
	
	List<Player> getAllPlayers(Server server) throws Exception;
	
	List<PlayerInfo> getAllPlayerInfos(Server server) throws Exception;
	
	Player findPlayerByPid(Integer ipid);
	
	Player findPlayerByName(String name,Server server) throws Exception;
	
	List<PayEntity> getAllbills(Server server) throws Exception;
	/**
	 * 获取玩家的offline信息
	 * @param ipid 玩家唯一id
	 * @param type 数据库类型
	 * @return
	 */
	Offline findOfflineByPid(Integer ipid,Server server) throws Exception;
	/**
	 * 获取玩家商店消费信息
	 * @param ipid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<LogShop> findLogShops(Integer ipid,Server server) throws Exception;
	/**
	 * 获取玩家的邮件信息
	 * @param ipid
	 * @param server
	 * @return
	 * @throws Exception
	 */
	List<MailInfo> findLogMails(Integer ipid,Server server) throws Exception;
	/**
	 * 获取物品购买信息
	 * @param server
	 * @return
	 * @throws Exception 
	 */
	List<ItemEntity> findItems(Server server) throws Exception;
	
	List<ChatEntity> findAllChat(Server server) throws Exception;
}
