package cn.hankchan.stu.cache.ehcache;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JavaConfig方式启动测试
 * @author hankchan101@gmail.com
 * <p>10 Nov 2016, 11:21:12
 */
public class EhCacheTestServiceTest extends SpringTestCase {

	final static Logger logger = LoggerFactory.getLogger(EhCacheTestServiceTest.class);
	
	@Autowired
	private EhCacheTestService ehCacheTestService;
	
	@Test
	public void getTimestampTest() throws InterruptedException {
		logger.info("start..");
		System.out.println("利用配置方式运行Spring容器。。");
		System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
		System.out.println("请稍后，将在11秒后再次调用。。。");
		Thread.sleep(11000);
		System.out.println("（配置了缓存10秒失效）再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
		logger.info("end..");
	}
}
