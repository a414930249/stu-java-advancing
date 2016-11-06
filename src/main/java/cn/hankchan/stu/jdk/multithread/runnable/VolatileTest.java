package cn.hankchan.stu.jdk.multithread.runnable;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

	public static void main(String[] args) {
		
		MyThread2[] attr = new MyThread2[10];
		for(int i = 0; i < 10; i++) {
			attr[i] = new MyThread2();
		}
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(attr[i]);
			t.start();
		}
	}
	
}

class MyThread2 implements Runnable {

//	private static int count = 0;
	private static AtomicInteger count = new AtomicInteger(0);
	
	public static void addCount() {
		for(int i = 0; i < 1000; i++) {
//			count++;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	@Override
	public void run() {
		addCount();
	}
	
}
