package cn.hankchan.stu.jdk.concurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 26 Nov 2016-23:11:09
 * <p>类说明: 如果运行两个线程：
 * 线程1先尝试获取锁1然后尝试获取锁2，成功了才能完成任务；
 * 线程2先尝试获取锁2然后尝试获取锁1，成功了才能完成任务。
 * 该方式非常容易产生死锁！
 * <p>但是这里使用的是tryLock()方法，在这种情况下，当前线程会尝试获得锁，如果锁没有被其他线程占用，成功获取并返回true。
 * 如果锁被其他线程占用，当前线程不会进行等待，而是立即返回false。也就是说，这样的方式不会产生死锁！不会死锁！不会死锁！
 */
public class TryLockReentrantLock implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lockSize;
	
	/**	控制加锁顺序 */
	public TryLockReentrantLock(int lockSize) {
		this.lockSize = lockSize;
	}
	
	public static void main(String[] args) {
		TryLockReentrantLock r1 = new TryLockReentrantLock(1);
		TryLockReentrantLock r2 = new TryLockReentrantLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
	
	@Override
	public void run() {
		// 构造器传入1时，执行该部分代码
		if(lockSize == 1) {
			while(true) {
				// 首先尝试获取lock1
				if(lock1.tryLock()) {
					try {
						try {
							// 模拟执行任务
							Thread.sleep(500);
						} catch (InterruptedException e) { }
						// 成功获取lock1之后，继续尝试获取lock2
						if(lock2.tryLock()) {
							try {
								// 获取lock2成功
								System.out.println(Thread.currentThread().getId() + ":My Job is DONE!");
								// 任务完成，return
								return;
							} finally {
								// 获取lock2最后需要释放
								lock2.unlock();
							}
						}
					} finally {
						// 获取lock1最后需要释放
						lock1.unlock();
					}
				}
			}
		} else {	// 构造器传入的值不是1，执行该部分代码
			while(true) {
				if(lock2.tryLock()) {
					try {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) { }
						if(lock1.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getId() + ":My Job is DONE!");
								return;
							} finally {
								lock1.unlock();
							}
						}
					} finally {
						lock2.unlock();
					}
				}
			}
		}
	}

}
