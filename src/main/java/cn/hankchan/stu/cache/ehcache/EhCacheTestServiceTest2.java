package cn.hankchan.stu.cache.ehcache;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassPathXML方式启动测试
 * @author hankchan101@gmail.com
 * <p>10 Nov 2016, 11:21:53
 */
public class EhCacheTestServiceTest2 {
	
	@Test
	public void test() throws InterruptedException {
		
		System.out.println("利用ClassPathXml方式运行容器。。。");
		@SuppressWarnings("resource")
		ApplicationContext xml = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext.xml");
		
		EhCacheTestService cacheTestService = (EhCacheTestService) xml.getBean("ehCacheTestService");
		
		System.out.println("第一次调用：" + cacheTestService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + cacheTestService.getTimestamp("param"));
		System.out.println("请稍后，将在11秒后再次调用。。。");
		Thread.sleep(11000);
		System.out.println("（配置了缓存10秒失效）再过11秒之后调用：" + cacheTestService.getTimestamp("param"));
	}
}
