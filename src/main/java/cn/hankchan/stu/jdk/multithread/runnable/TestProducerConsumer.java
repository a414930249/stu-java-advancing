package cn.hankchan.stu.jdk.multithread.runnable;

import java.util.concurrent.CountDownLatch;

public class TestProducerConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Runnable producer = new Producer(clerk);
		Runnable consumer = new Consumer(clerk);

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		Thread t3 = new Thread(consumer);
		Thread t4 = new Thread(producer);
		Thread t5 = new Thread(consumer);
		
		t1.setName("沃尔玛");
		t2.setName("黄志龙");
		t3.setName("杨子毅");
		t4.setName("宝洁");
		t5.setName("王教授");
		
		t1.start();
		t4.start();
		t2.start();
		t3.start();
		t5.start();
	}
	
}

/**
 * 共享数据类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 26 Jul 201621:53:23
 * <p>类说明:
 */
class Clerk {
	CountDownLatch countDownLatch = new CountDownLatch(1);
	int product;
	
	public synchronized void addProduct() {
		if(product >= 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			product++;
			System.out.println("生产者( " + Thread.currentThread().getName() + " )生产了第[ " + product + " ]个产品");
			notifyAll();
		}
	}
	
	public synchronized void consumProduct() {
		if(product <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("消费者( " + Thread.currentThread().getName() + " )消费了第[ " + product + " ]个产品");
			product--;
			notifyAll();
		}
	}
}

/**
 * 生产者
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 26 Jul 201621:52:55
 * <p>类说明:
 */
class Producer implements Runnable {

	Clerk clerk;
	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
			try {
				Thread.currentThread().sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("生产者开始生产。。");
			clerk.addProduct();
		}
	}
	
}

/**
 * 消费者
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 26 Jul 201621:53:02
 * <p>类说明:
 */
class Consumer implements Runnable {

	Clerk clerk;
	
	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("消费者开始消费。。");
			clerk.consumProduct();
		}
	}
	
}