package cn.hankchan.stu.jdk.multithread.runnable;

class Thread2 extends Thread {
	@Override
	public void run() {
		super.run();
		try {
			System.out.println(Thread.currentThread().getName() + "==> running....");
			Thread.sleep(10000);
			System.out.println(Thread.currentThread().getName() + "==> after 10 seconds sleep...");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + "==> catch InterruptedException in run...");
			e.printStackTrace();
		}
	}
}	
public class ThreadMain {
	public static void main(String[] args) {
		
		Thread2 t = new Thread2();
		t.start();
		try {
			System.out.println(Thread.currentThread().getName() + "==> prepare to sleep 2 seconds..");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "==> after sleep 2 seconds..");
			t.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*TrueSaleTicketThread t1= new TrueSaleTicketThread("t1");
		new Thread(t1).start();
		new Thread(t1).start();
		new Thread(t1).start();*/
//		SaleTicketThread T1 = new SaleTicketThread("窗口1");
//		SaleTicketThread T2 = new SaleTicketThread("窗口2");
//		SaleTicketThread T3 = new SaleTicketThread("窗口3");
//		new Thread(T1).start();
//		new Thread(T2).start();
//		new Thread(T3).start();
		
//		MyThread myThread = new MyThread("T1");
//		MyThread myThread2 = new MyThread("T2");
//		new Thread(myThread).start();
//		new Thread(myThread2).start();
	}
}
