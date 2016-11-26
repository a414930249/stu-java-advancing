package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁：中断响应
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 26 Nov 2016-22:36:49
 * <p>类说明: 对于synchronized来说如果一个线程在等待锁，那么有两种结果：要么获得该锁然后继续执行；要么保持等待。
 * 使用重入锁，则提供了多一种可能！那就是线程可以被中断！！可以被中断！！被中断！！
 * 就是说在等待锁的过程中，程序可以根据需要取消对锁的请求。
 * <p>lockInterruptibly()方法是可以响应中断的对锁请求的方法
 */
public class InterruptReentrantLock implements Runnable {

	public static ReentrantLock reentrantLock1 = new ReentrantLock();
	public static ReentrantLock reentrantLock2 = new ReentrantLock();
	
	int lockSize;	// 控制加锁
	/**	控制加锁顺序，方便构成死锁 */
	public InterruptReentrantLock(int lockSize) {
		this.lockSize = lockSize;
	}
	
	public static void main(String[] args) throws InterruptedException {
		InterruptReentrantLock r1 = new InterruptReentrantLock(1);
		InterruptReentrantLock r2 = new InterruptReentrantLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		// 中断其中一个线程
		t2.interrupt();
	}
	
	@Override
	public void run() {
		try {
			if(lockSize == 1) {
				reentrantLock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					reentrantLock2.lockInterruptibly();
				}
			} else {
				reentrantLock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					reentrantLock1.lockInterruptibly();
				}
			}
			System.out.println(Thread.currentThread().getId() + ": done...");
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(reentrantLock1.isHeldByCurrentThread()) {
				reentrantLock1.unlock();
			}
			if(reentrantLock2.isHeldByCurrentThread()) {
				reentrantLock2.unlock();
			}
			System.out.println(Thread.currentThread().getId() + ":线程退出！！");
		}
	}

}
