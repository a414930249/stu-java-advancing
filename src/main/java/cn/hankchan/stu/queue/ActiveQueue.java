package cn.hankchan.stu.queue;

import java.util.Stack;

public class ActiveQueue {

	private Stack<MethodRequest> _queue;
	private final static int QUEUE_SIZE = 20;
	public ActiveQueue() {
		_queue = new Stack<>();
	}
	/**
	 * 进入队列
	 * @param request
	 */
	public synchronized void enqueue(MethodRequest request) {
		while(_queue.size() > QUEUE_SIZE) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		_queue.push(request);
		notifyAll();
		System.out.println("Leave Queue..");
	}
	/**
	 * 退出队列
	 * @return
	 */
	public synchronized MethodRequest dequeue() {
		MethodRequest mr;
		while(_queue.empty()) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		mr = (MethodRequest) _queue.pop();
		notifyAll();
		return mr;
	}
}
