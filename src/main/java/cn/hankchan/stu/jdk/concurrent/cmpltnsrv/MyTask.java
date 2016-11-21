package cn.hankchan.stu.jdk.concurrent.cmpltnsrv;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {

	private String stamp;
	public MyTask(String stamp) {
		this.stamp = stamp;
	}
	public MyTask() { }
	
	@Override
	public String call() throws Exception {
		int sleepTime = new Random().nextInt(1000);
		Thread.sleep(sleepTime);
		String result = stamp + "; time: " + sleepTime;
		System.out.println(stamp + " is finished..");
		return result;
	}

}
