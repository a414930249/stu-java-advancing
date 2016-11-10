package cn.hankchan.stu.cache.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("ehCacheTestService")
public class EhCacheTestServiceImpl implements EhCacheTestService {

	@Cacheable(value="cacheTest",key="#param")
	@Override
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

}
