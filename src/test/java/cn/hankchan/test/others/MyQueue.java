package cn.hankchan.test.others;

import java.util.Stack;

public class MyQueue<T> {

	private Stack<T> _stack;
	private static final int MAX_CAPITAL_SIZE = 20;

	public MyQueue() {
		_stack = new Stack<>();
	}
	
	public synchronized T outerQueue() {
		T t;
		while (_stack.empty()) {
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
	
	public synchronized void enterQueue(T t) {
		while (_stack.size() > MAX_CAPITAL_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		_stack.push(t);
		notifyAll();
	}
}
