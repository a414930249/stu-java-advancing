package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的搭档：Condition条件
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Nov 2016-23:26:22
 * <p>类说明: 同Object.wait()和Object.notify()的作用大致相同，这两个是同synchronized搭配使用。
 * Condition与重入锁相关联！通过Lock接口（重入锁也实现了这个接口）的Condition newCondition()方法能够生成一个与当前重入锁绑定的Condition实例。
 * 通过Condition对象，可以让线程在合适的时间等待，或者在某一个特定时刻得到通知，继续执行。
 * <p>和Object.wait()和notify()方法一样，当线程使用Condition.await()方法时，要求线程持有相关的重入锁，在调用后，这个线程会释放该锁。
 * <p>同理，在Condition.signal()方法调用时，也要求线程先获得相关的锁。
 */
public class ConditionReentrantLock implements Runnable {

	public static ReentrantLock reentrantLock = new ReentrantLock();
	public static Condition condition = reentrantLock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		ConditionReentrantLock r = new ConditionReentrantLock();
		Thread t1 = new Thread(r);
		t1.start();
		Thread.sleep(2000);
		
		/** 下面通知线程t1继续执行 */
		// 当t1释放reentrantLock后，由于在main中需要调用signal()方法去唤醒t1，
		// 所以main线程也需要拿到该锁（reentrantLock）才可以调用signal()方法
		reentrantLock.lock();	// 拿到锁
		condition.signal();		// 通知t1继续执行
		reentrantLock.unlock();	// 释放锁，如果屏蔽本行代码，虽然唤醒了t1线程，但是因为它无法重新获得锁，所以无法真正的继续执行
		/** 通知结束 */
	}
	
	@Override
	public void run() {
		try {
			reentrantLock.lock();	// 拿到锁
			// 下面设定线程等待
			condition.await();
			// 等待如果收到继续执行的通知，就结束等待，继续执行下面代码
			System.out.println("Thread is going on");
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			reentrantLock.unlock();		// 需要主动释放锁
		}
	}
	
}
