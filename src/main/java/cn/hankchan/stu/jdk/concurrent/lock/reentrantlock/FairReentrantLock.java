package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 26 Nov 2016-23:42:47
 * <p>类说明:
 */
public class FairReentrantLock implements Runnable {

	public static ReentrantLock fairLock = new ReentrantLock(true);

	public static void main(String[] args) {
		FairReentrantLock r1 = new FairReentrantLock();
		Thread t1 = new Thread(r1, "t1");
		Thread t2 = new Thread(r1, "t2");
		t1.start();
		t2.start();
	}
	@Override
	public void run() {
		while(true) {
			try {
				fairLock.lock();	
				System.out.println(Thread.currentThread().getName() + "：获得锁");
			} finally {
				fairLock.unlock();
			}
		}
	}

}
