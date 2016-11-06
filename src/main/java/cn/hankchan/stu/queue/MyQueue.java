package cn.hankchan.stu.queue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	//1 存储的属性
	private LinkedList<Object> queue = new LinkedList<>();
	//2 累加器
	private AtomicInteger count = new AtomicInteger(0);
	//3 最大值
	private final int maxSize;
	private final int minSize = 0;
	
	//用来加锁的对象
	private final Object lock = new Object();
	
	public int getSize() {
		return this.count.get();
	}
	//构造器
	public MyQueue(int size) {
		this.maxSize = size;
	}
	
	public void put(Object o) {
		synchronized (lock) {
			while(count.get() == this.maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.add(o);
			count.incrementAndGet();
			lock.notify();
			System.out.println("添加了一个对象：" + o);
		}
	}
	public Object take() {
		Object o = new Object();
		synchronized (lock) {
			while(count.get() == this.minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			o = queue.removeFirst();
			count.decrementAndGet();
			lock.notify();
			System.out.println("移除了一个对象：" + o);
		}
		return o;
	}
	
	public static void main(String[] args) {
		MyQueue mq = new MyQueue(5);
		mq.toString();
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		System.out.println("当前长度：" + mq.getSize());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		}, "t1");
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mq.take();
				mq.take();
			}
		}, "t2");
		t2.start();
		
		try {
			//2秒后运行第二个进程(t2)
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}





