package cn.hankchan.stu.jdk.multithread.thread;

public class PrintString implements Runnable {

	private boolean isContinuePrint = true;
	public boolean isContinuePrint() {
		return isContinuePrint;
	}
	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	
	public void print() {
		try {
			while(isContinuePrint) {
				System.out.println("run print String ... thread name = " + Thread.currentThread().getName());
				Thread.sleep(1000);
			}
			System.out.println("out of while..");
		} catch (InterruptedException e) {
			System.out.println("in the catch..");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		print();
	}

}
