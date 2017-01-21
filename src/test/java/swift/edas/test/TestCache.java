package swift.edas.test;

import org.junit.Test;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCache {

	@Test
	public void testCacheConfig() {
		@SuppressWarnings("resource")
		ApplicationContext xml = new AnnotationConfigApplicationContext(CacheConfig.class);
		CacheManager cacheManager = (CacheManager) xml.getBean("cacheManager");
		System.out.println(cacheManager);
	}
}
