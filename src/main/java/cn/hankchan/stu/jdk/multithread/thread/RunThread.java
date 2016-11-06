package cn.hankchan.stu.jdk.multithread.thread;

public class RunThread extends Thread {

	private boolean flag = true;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		System.out.println("into the run .... ");
		while(flag) {

		}
		System.out.println("the thread is stop....");
	}
}
