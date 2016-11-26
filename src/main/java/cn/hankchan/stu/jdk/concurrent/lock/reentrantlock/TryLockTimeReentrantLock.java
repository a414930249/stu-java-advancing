package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 26 Nov 2016-23:49:08
 * <p>类说明: tryLock()接收两个参数，表示等待时长。如果超过等待时长仍然没有获得锁，就返回false
 */
public class TryLockTimeReentrantLock implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		TryLockTimeReentrantLock r = new TryLockTimeReentrantLock();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}
	
	@Override
	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)) {
				System.out.println("SUCCESS");
				Thread.sleep(6000);
			} else {
				// 由于上面代码占用锁的时间长达6s，线程等待时间设定为最多等待5秒，所以请求锁将失败
				System.out.println("get lock failed..");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

}
