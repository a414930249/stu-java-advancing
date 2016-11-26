package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁示例
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 26 Nov 2016-22:23:19
 * <p>类说明: 必须手动说明何时释放锁，何时加锁。如果一个线程多次获得锁，就必须释放相同次数的锁！
 */
public class ReenterLock implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;
	
	@Override
	public void run() {
		for(int j = 0; j < 10000000; j++) {
			// 使用重入锁保护临界区资源
			lock.lock();
//			lock.lock();	// 多次获得锁
			try {
				i++;
			} finally {
				// 保护到此为止
				lock.unlock();
//				lock.lock();	// 释放相同次数的锁
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReenterLock testRunnable = new ReenterLock();
		Thread t1 = new Thread(testRunnable);
		Thread t2 = new Thread(testRunnable);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
	
}
