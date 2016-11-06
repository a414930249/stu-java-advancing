package cn.hankchan.stu.queue;

public class Test {

	public static void main(String[] args) {
		Service s = new ServiceProxy();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c = new Client(s);
				c.requestService();
			}
		});
		Thread t4 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Client c = new Client(s);
						c.requestService();
					}
				});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c = new Client(s);
				c.requestService();
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c = new Client(s);
				c.requestService();
			}
		});
//		Client c = new Client(s);
//		c.requestService();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}
