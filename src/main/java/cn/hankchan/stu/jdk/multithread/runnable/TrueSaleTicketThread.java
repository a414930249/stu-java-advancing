package cn.hankchan.stu.jdk.multithread.runnable;

public class TrueSaleTicketThread implements Runnable {

	private Integer tickets = 20;
	
	private String name;
	
	public TrueSaleTicketThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {

		for(int i = 0; i < 20; i++){
			synchronized (this.tickets) {
				if(this.tickets > 0){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("我取第" + (21 - tickets) + "张票");
					tickets--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(tickets >=0){
						System.out.println("剩余" + tickets + "张票");
					}
				}
			}
		}
	}

}
