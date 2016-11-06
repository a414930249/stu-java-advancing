package cn.hankchan.stu.jdk.multithread.thread;

import org.junit.Test;

public class Run {
	
	@Test
	public void testLambda() throws InterruptedException {
		Runnable runnable = () -> {
			System.out.println("running with Lambda..");
		};
		new Thread(runnable).start();
		Thread.sleep(2000);
		System.out.println("end...");
	}
	
	@Test
	public void testRunThread() {
		try {
			RunThread thread = new RunThread();
			thread.start();
			Thread.sleep(1000);
			thread.setFlag(false);
			System.out.println("is been the false");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPrintString() throws InterruptedException {
		PrintString printString = new PrintString();
		new Thread(printString).start();
		Thread.sleep(3000);
		System.out.println("我打算停止这个线程-------------------------------------" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
	}
	
	@Test
	public void testMyThread2() throws InterruptedException {
		MyThread2 t = new MyThread2();
		t.start();
		Thread.sleep(100);
		t.interrupt();
	}

	@Test
	public void testMyThread1() {
		try {
			Mythread1 th = new Mythread1();
			th.start();
			Thread.sleep(200);
			th.interrupt();
		} catch (InterruptedException e) {
			System.out.println("main 's catch...");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
	
	/**
	 * 通过状态判断停止循环
	 */
	@Test
	public void testMyThread() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(2000);
			thread.interrupt();
		} catch (InterruptedException e) {
			System.out.println("main 's catch..");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
	
	/******************** other test **************************************************************/
	@Test
	public void test1() {
		Interface2 i = (a, b) -> {
			System.out.println(a + ";" + b);
		};
		System.out.println("end...");
	}
	
	interface Interface2 {
		public abstract void method(int a, String str);
	}

}
