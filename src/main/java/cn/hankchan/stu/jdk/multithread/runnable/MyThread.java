package cn.hankchan.stu.jdk.multithread.runnable;

public class MyThread implements Runnable {

	private String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println("线程:" + name + " 正在运行中。。。" + i);
		}
	}

}
