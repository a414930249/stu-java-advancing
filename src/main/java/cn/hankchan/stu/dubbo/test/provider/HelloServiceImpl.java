package cn.hankchan.stu.dubbo.test.provider;

import cn.hankchan.stu.dubbo.test.consumer.HelloService;

/**
 * 公共接口的实现
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 24 Oct 2016-14:17:44
 * @detail
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello() {
		System.out.println("start to called by consumer..");
		return "hello, I am Service Implements..";
	}

}
