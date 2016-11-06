package cn.hankchan.stu.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存管理类
 * @author Hank_  Email:hicth_chan@163.com
 * @version 创建时间: 5 Jul 201617:19:19
 * 类说明: ConcurrentHashMap是线程安全的，支持并发。
 * @param <T>
 */
public class MyCacheManager<T> {

	private Map<String, T> maps = new ConcurrentHashMap<>();
	
	/**
	 * 新增缓存数据
	 * @param k
	 * @param t
	 */
	public void addAcct(String k, T t) {
		maps.put(k, t);
	}
	/**
	 * 根据key从缓存中获取数据
	 * @param k
	 * @return
	 */
	public T getAcct(String k) {
		return maps.get(k);
	}
	/**
	 * 根据key从缓存删除该数据
	 * @param k
	 */
	public void delCache(String k){
		if(maps.containsKey(k)) {
			maps.remove(k);
		}
	}
	/**
	 * 清空缓存
	 */
	public void delCache() {
		maps.clear();
	}
}
