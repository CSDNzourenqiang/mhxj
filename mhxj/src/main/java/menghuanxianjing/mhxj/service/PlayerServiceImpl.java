package menghuanxianjing.mhxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import menghuanxianjing.mhxj.repository.PlayerRepository;


@Service
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	PlayerRepository playerRepository;

	@Override
	public List<Player> getAllPlayers(Server server) throws Exception {
		return playerRepository.findAllPlayers(server);
	}

	@Override
	public Player findPlayerByPid(Integer ipid) {
		return playerRepository.findById(ipid).get();
	}

	@Override
	public Player findPlayerByName(String name,Server server) throws Exception {
		return playerRepository.findByName(name,server);
	}

	@Override
	public List<PayEntity> getAllbills(Server server) throws Exception {
		return playerRepository.findAllBills(server);
	}

	@Override
	public List<PlayerInfo> getAllPlayerInfos(Server server) throws Exception {
		return playerRepository.findAllPlayerInfos(server);
	}

	@Override
	public Offline findOfflineByPid(Integer ipid, Server server) throws Exception {
		return playerRepository.findOfflineByPid(ipid,server);
	}

	@Override
	public List<LogShop> findLogShops(Integer ipid, Server server) throws Exception {
		return playerRepository.findLogShopByPid(ipid, server);
	}

	@Override
	public List<MailInfo> findLogMails(Integer ipid, Server server) throws Exception {
		return playerRepository.findLogMailByPid(ipid, server);
	}

	@Override
	public List<ItemEntity> findItems(Server server) throws Exception {
		return playerRepository.findItems(server);
	}

	@Override
	public List<ChatEntity> findAllChat(Server server) throws Exception {
		return playerRepository.findAllChat(server);
	}



}
