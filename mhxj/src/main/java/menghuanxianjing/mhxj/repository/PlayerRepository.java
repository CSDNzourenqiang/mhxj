package menghuanxianjing.mhxj.repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Severity;
import javax.swing.SortOrder;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import menghuanxianjing.config.CommonConfig;
import menghuanxianjing.config.MongoTemplateMgr;
import menghuanxianjing.mhxj.dao.MoPlayerMapper;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.pojo.ChatEntity;
import menghuanxianjing.mhxj.pojo.ItemEntity;
import menghuanxianjing.mhxj.pojo.LogShop;
import menghuanxianjing.mhxj.pojo.MailInfo;
import menghuanxianjing.mhxj.pojo.NoticeEntity;
import menghuanxianjing.mhxj.pojo.Offline;
import menghuanxianjing.mhxj.pojo.PayEntity;
import menghuanxianjing.mhxj.pojo.Player;
import menghuanxianjing.mhxj.pojo.PlayerInfo;
import menghuanxianjing.utils.Constants;

@Repository
public class PlayerRepository implements MongoRepository<Player, Integer>{
	
	@Autowired
	CommonConfig commonConfig;
	
	@Autowired
	@Qualifier(value = "gsCenterMongoTemplate")
	private MongoTemplate gsCenterMongoTemplate;
	
	
	@Autowired
	private MongoTemplateMgr mongoTemplateMgr;
	
	@Override
	public Page<Player> findAll(Pageable pageable) {
		
		return null;
	}

	@Override
	public <S extends Player> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Player> findById(Integer id) {
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		return false;
	}

	@Override
	public Iterable<Player> findAllById(Iterable<Integer> ids) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public void delete(Player entity) {
		
	}

	@Override
	public void deleteAll(Iterable<? extends Player> entities) {
		
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public <S extends Player> Optional<S> findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Player> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends Player> boolean exists(Example<S> example) {
		return false;
	}

	@Override
	public <S extends Player> List<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@Override
	public List<Player> findAll() {
		
		return null;
	}

	@Override
	public List<Player> findAll(Sort sort) {
		return null;
	}

	@Override
	public <S extends Player> S insert(S entity) {
		return null;
	}

	@Override
	public <S extends Player> List<S> insert(Iterable<S> entities) {
		return null;
	}

	@Override
	public <S extends Player> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}
	/**
	 * 获取所有得订单信息
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<PayEntity> findAllBills(Server server) throws Exception{
		Query query=Query.query(new Criteria());
		query.skip(0);
		query.limit(20);
		List<PayEntity> bills=gsCenterMongoTemplate.find(query,PayEntity.class);
		if (bills.size()==0) {
			return null;
		}
		return bills;
	}
	public List<NoticeEntity> getAllNotice(){
		Query query=Query.query(new Criteria());
		List<NoticeEntity> notice_list=gsCenterMongoTemplate.find(query,NoticeEntity.class);
		return notice_list;
	}
	/**
	 * 通过玩家昵称获取玩家详情
	 * @param name
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Player findByName(String name,Server server) throws Exception {
		Query query=Query.query(Criteria.where("name").is(name));
		List<Player> players=this.getTemplate(server).find(query,Player.class);
		if (players.size()==0) {
			System.out.println("获取到的player为空");
			Player player=new Player();
			player.set_id("无");
			player.setAccount("无");
			player.setName("未找到");
			player.setPid(0);
			return player;
		}
		return players.get(0);
	}
	public List<Player> findAllPlayers(Server server) throws Exception {
		Query query=Query.query(new Criteria());
		List<Player> players=this.getTemplate(server).find(query,Player.class);
		if (players.size()==0) {
			return null;
		}
		return players;
	}
	public List<PlayerInfo> findAllPlayerInfos(Server server) throws Exception{
		Query query=Query.query(new Criteria());
		List<PlayerInfo> players=this.getTemplate(server).find(query,PlayerInfo.class);
		if (players.size()==0) {
			return null;
		}
		return players;
	}
	//获取商店日志信息
	public List<LogShop> findLogShopByPid(Integer ipid,Server server) throws Exception{
		Query query1=Query.query(Criteria.where("pid").is(ipid));
		List<String> dbnames=Constants.getDbnameLog( Integer.parseInt(commonConfig.getOpen_month()), Integer.parseInt(commonConfig.getOpen_year()));
		
		List<LogShop> list_all=new ArrayList<LogShop>();
		for(String dbname:dbnames) {
			server.setDbName(dbname);
			List<LogShop> list1=this.getTemplate(server).find(query1, LogShop.class);
			list_all.addAll(list1);
		}
		
		return list_all;
	}
	//获取邮件日志信息
	public List<MailInfo> findLogMailByPid(Integer ipid,Server server) throws Exception{
		Query query1=Query.query(Criteria.where("receiver_id").is(ipid));
		Query query2=Query.query(Criteria.where("receiver_id").is(ipid+""));
		
		
		List<String> dbnames=Constants.getDbnameLog( Integer.parseInt(commonConfig.getOpen_month()), Integer.parseInt(commonConfig.getOpen_year()));
		
		List<MailInfo> list_all=new ArrayList<MailInfo>();
		for(String dbname:dbnames) {
			server.setDbName(dbname);
			List<MailInfo> list2=this.getTemplate(server).find(query2, MailInfo.class);
			List<MailInfo> list1=this.getTemplate(server).find(query1, MailInfo.class);
			list_all.addAll(list1);
			list_all.addAll(list2);
		}
		return list_all;
	}
	public List<ChatEntity> findAllChat(Server server) throws Exception{
		Query query=Query.query(new Criteria());	
		query.with(Sort.by(Direction.DESC, "_time")); 
		server.setDbName("chatlog");
		List<ChatEntity> list=this.getTemplate(server).find(query, ChatEntity.class);
		return list;
	}
	public List<ItemEntity> findItems(Server server) throws Exception {
		Query query1=Query.query(Criteria.where("reason").is("npc商城购买"));
		
		List<String> dbnames=Constants.getDbnameLog( Integer.parseInt(commonConfig.getOpen_month()), Integer.parseInt(commonConfig.getOpen_year()));
		
		List<ItemEntity> list_all=new ArrayList<ItemEntity>();
		for(String dbname:dbnames) {
			server.setDbName(dbname);
			List<ItemEntity> list1=this.getTemplate(server).find(query1, ItemEntity.class);
			list_all.addAll(list1);
		}
		return list_all;
	}

	/**
	 * 获取玩家offline信息
	 * @param ipid
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public Offline findOfflineByPid(Integer ipid,Server server) throws Exception {
		Query query=Query.query(Criteria.where("pid").is(ipid));
		Offline offline=this.getTemplate(server).findOne(query, Offline.class);
		return offline;
	}
	 
	 private MongoTemplate getTemplate(Server server) throws Exception {
		 if(server == null){
	            throw new Exception("MongoDBType is null!");
	     }
		 return mongoTemplateMgr.getMongoTemplate(server.getHost(), server.getDbName());
	 }

}
