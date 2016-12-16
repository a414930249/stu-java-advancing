package cn.hankchan.stu.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wombat {

	public static Logger logger = LoggerFactory.getLogger(Wombat.class);
	
	private String username;
	private int isOnline;

	public static void main(String[] args) {
		System.out.println("start...........");
		logger.info("start..");
		logger.error("test debug: {}", new Exception("test"));
		Wombat wombat = new Wombat();
		wombat.showLoginUser(0);
	}
	public void showLoginUser(int a) {
		String state = "在线";
		username = "dbtinger";
		if(a > 0) {
			isOnline = a;
		}
		if(isOnline <= 0) {
			state = "离线";
		}
		logger.debug("登陆名为:{}, 登陆状态为:{}", username, state);
	}
	
}
