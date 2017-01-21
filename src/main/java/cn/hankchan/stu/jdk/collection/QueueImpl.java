package cn.hankchan.stu.jdk.collection;

import java.util.Stack;

public class QueueImpl<T> {

	private Stack<T> _stack;
	private static final int DEFAULT_QUEUE_CAPITAL = 20;  // 默认队列容量
	public QueueImpl() {
		_stack = new Stack<>();
	}
	/**
	 * 定义加入队列的方法
	 */
	public synchronized void addToQueue(T t) {
		while(_stack.size() > DEFAULT_QUEUE_CAPITAL) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		_stack.push(t);
		notifyAll();
		System.out.println("added to queue..");
	}
	/**
	 * 定义从队列取出任务的方法
	 */
	public synchronized T removeFromQueue() {
		T t;
		while(_stack.empty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = _stack.pop();
		notifyAll();
		return t;
	}
}
