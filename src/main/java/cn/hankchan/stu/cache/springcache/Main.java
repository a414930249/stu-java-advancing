package cn.hankchan.stu.cache.springcache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring/application-springcache.xml");
		AccountService service= (AccountService) context.getBean("accountServiceBean");
		System.out.println("query start");
		// 第一次查询，结果应该是从数据库中查找
		System.out.println("first QUERY...");
		service.getAccountByName("DavidBeckham");
		// 第二次查询，结果应该是从缓存中获取
		System.out.println("second QUERY...");
		service.getAccountByName("DavidBeckham");
		System.out.println("query stop");
		System.out.println("--------------TEST - 2------------------");
		System.out.println("first get time...start");
		String result = service.getTime("time");
		System.out.println(result);
		System.out.println("second get time...start");
		String result2 = service.getTime("time");
		System.out.println(result2);
	}
}
