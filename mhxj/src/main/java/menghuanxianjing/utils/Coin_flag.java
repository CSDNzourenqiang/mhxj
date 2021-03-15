package menghuanxianjing.utils;

public enum Coin_flag {
	CON_FLAG_GOLD(1,"1003"),//水晶
	CON_FLAG_ENERGY(14,"1020"),//体力
	CON_FLAG_SKIN(9,"1017"),//皮肤券
	CON_FLAG_MEDAL(3,"1011"),//勋章
	CON_FLAG_ARENA(4,"1009"),//荣誉
	CON_FLAG_COIN(2,"1002");//金币
	
	private int id;
	private String name;
	private Coin_flag(int id,String name) {
		this.setId(id);
		this.setName(name);
	}
	
	public static Coin_flag getCoinId(String name) {
		for(Coin_flag coin_flag:values()) {
			if (coin_flag.getName().equals(name)) {
				return coin_flag;
			}
		}
		return null;
	}
	public static Coin_flag getValue(int id) {
		for(Coin_flag coin_flag:values()) {
			if (coin_flag.getId()==id) {
				return coin_flag;
			}
		}
		return null;
	}
//	public static enum COIN_FLAG{
//		1002:1
//	}
//	COIN_FLAG = {
//			COIN_GOLD = 1,          --水晶
//			COIN_COIN = 2,          --金币
//			COIN_MEDAL = 3,        --勋章
//			COIN_ARENA = 4,        --荣誉
//			COIN_ACTIVE = 5,        --活跃度
//			COIN_ORG_OFFER = 6, --帮派贡献
//			COIN_ORG_CASH = 7,  --帮派资金
//			COIN_TRAPMINE_POINT = 8, --暗雷探索点
//			COIN_SKIN = 9, --皮肤卷0_0
//			COIN_TRAVEL = 10, --游历积分
//			COIN_ORG_PRESTIGE = 11 , --公会声望
//			COIN_COLOR =  12, --彩晶
//			COIN_RMB = 13, --人民币
//			COIN_ENERGY = 14,--体力
//			}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
