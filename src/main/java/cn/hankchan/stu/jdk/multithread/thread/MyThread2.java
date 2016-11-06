package cn.hankchan.stu.jdk.multithread.thread;

public class MyThread2 extends Thread {
	
	@Override
	public void run() {
		super.run();
		while(true) {
			if(this.isInterrupted()) {
				System.out.println("停止了！！");
				return;
			}
			System.out.println("timer:" + System.currentTimeMillis());
		}
	}

}
