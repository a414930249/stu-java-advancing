package cn.hankchan.stu.cache.springcache;

import org.springframework.cache.annotation.Cacheable;

import cn.hankchan.stu.entity.Account;

public class AccountService {

	/** 
	 * 使用了一个缓存名 accountCache
	 * Cacheable注解的value值需要配置在spring-*.xml配置文件中，它能够根据方法的请求参数对其结果进行缓存！
	 * 这个注释的意思是，当调用这个方法的时候，会从一个名叫 accountCache 的缓存中查询，
	 * 如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，否则返回缓存中的对象。
	 * 这里的缓存中的 key 就是参数 userName，value 就是 Account 对象。
	 * “accountCache”缓存是在 spring*.xml 中定义的名称。
	 * 因为加入了 spring，所以我们还需要一个 spring 的配置文件来支持基于注释的缓存
	 */
	@Cacheable(value="accountCache")
	public Account getAccountByName(String username) {
		// 内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account: " + username);
		return getFromDB(username);
	}
	
	@Cacheable(value="time")
	public String getTime(String time) {
		Long timestamp = System.currentTimeMillis();
		return (time + timestamp.toString());
	}

	private Account getFromDB(String username) {
		System.out.println("real querying db...wait...;result: " + username);
		return new Account(username);
	}
}
