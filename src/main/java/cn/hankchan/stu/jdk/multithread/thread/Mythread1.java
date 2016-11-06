package cn.hankchan.stu.jdk.multithread.thread;

public class Mythread1 extends Thread {

	@Override
	public void run() {
		super.run();
		try {
			System.out.println("run start.....");
			Thread.sleep(200000);
			System.out.println("run end.....");
		} catch (InterruptedException e) {
			System.out.println("在沉睡中被停止。。进入到catch了！" + this.isInterrupted());
			e.printStackTrace();
		}
	}
}
