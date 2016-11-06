package cn.hankchan.stu.jdk.multithread.runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private volatile static List<String> list = new ArrayList<>();
	
	public void add() {
		list.add("hello world..");
	}
	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		final CountDownLatchTest target = new CountDownLatchTest();
		
		final Object lock = new Object();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//					synchronized (lock) {
						for(int i = 0; i < 10; i++) {
							target.add();
							System.out.println("线程:" + Thread.currentThread().getName() + " 添加了一个元素");
							Thread.sleep(500);
							if(target.size() == 5) {
								System.out.println("已经发出通知。。");
								countDownLatch.countDown();
//								lock.notify();
							}
						}
//					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				synchronized (lock) {
					if(target.size() != 5) {
						try {
							countDownLatch.await();
//							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("线程:" + Thread.currentThread().getName() + " 收到通知停止执行。。");
					throw new RuntimeException();
//				}
			}
		}, "t2");
		
		t2.start();
		t1.start();
	}
}
