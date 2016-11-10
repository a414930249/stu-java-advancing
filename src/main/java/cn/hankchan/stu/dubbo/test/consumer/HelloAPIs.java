package cn.hankchan.stu.dubbo.test.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hankchan.dubbo.service.HelloService;

@Controller
public class HelloAPIs {

	@Autowired
	HelloService helloService;
	
	@RequestMapping("/api/hello")
	public @ResponseBody String helloApi() {
		helloService.sayHello();
		return "hello..I am APIs.";
	}
}
