package cn.hankchan.stu.queue;

public class ConnThreadLocal {

	private static ThreadLocal<String> th = new ThreadLocal<>();

	@SuppressWarnings("static-access")
	public void getTh() {
		System.out.println(Thread.currentThread().getName() + ", the th is : " + this.th.get());
	}

	@SuppressWarnings("static-access")
	public void setTh(String th) {
		this.th.set(th);
	}
	
	public static void main(String[] args) {
		
		ConnThreadLocal ct = new ConnThreadLocal();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("dbtinger");
				ct.getTh();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ct.getTh();
			}
		}, "t2");
		t1.start();
		t2.start();
	}
	
}
