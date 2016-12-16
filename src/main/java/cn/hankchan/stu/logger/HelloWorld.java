package cn.hankchan.stu.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

	public static Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	public static void main(String[] args) {
		try {
			System.out.println("start....");
			logger.error("Test Debug {}", new Exception("debug"));
			int i = 0;
			int j = 100;
			System.out.println(j/i);
		} catch (Exception e) {
			logger.debug("错误信息: {} ...", e.getMessage());
		}
		logger.info("Hello World..");
	}
}
