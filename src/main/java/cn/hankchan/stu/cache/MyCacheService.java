package cn.hankchan.stu.cache;

public class MyCacheService {
	
	private MyCacheManager<Account> cacheManager;
	
	public MyCacheService() {
		cacheManager = new MyCacheManager<>();
	}
	
	public Account getAccount(String name) {
		Account result = cacheManager.getAcct(name); //从缓存中查询
		if(result != null) {
			System.out.println("get datas from CACHE.." + result);
			return result; //在缓存中，直接获取数据
		}
		result = getFromDB(name); //从数据库获取
		if(result != null) {
			cacheManager.addAcct(name, result); //将数据库获取到的数据放入缓存中
		}
		return result;
	}
	
	public void reload() {
		System.out.println("清空缓存信息。。。");
		cacheManager.delCache();
	}

	public Account getFromDB(String name) {
		System.out.println("get datas from DB.." + name);
		return new Account(name);
	}
	
}
