package cn.hankchan.stu.jdk.multithread.runnable;

public class TestThread {

	public static void main(String[] args) {
		WindowSale w = new WindowSale();
		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		t1.start();
		t2.start();
		
	}
	
}

/**
 * 存在线程安全问题！！
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 23 Jul 201618:03:58
 * <p>类说明:
 */
class WindowSale implements Runnable {

	int tickets = 100;
	Object obj = new Object();
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this) {
//		synchronized(obj) {
			while(true) {
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(tickets > 0) {
					System.out.println("窗口:" + Thread.currentThread().getName() + ";售出票号==>>" + tickets--);
				} else {
					break;
				}
			}
		}
		System.out.println(obj);
	}
	
}
