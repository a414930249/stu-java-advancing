package cn.hankchan.stu.jdk.multithread.runnable;

public class SaleTicketThread implements Runnable {

	private static Integer tickets = 20;
	private String name;
	public Integer getTickets() {
		return tickets;
	}
	public void setTickets(Integer tickets) {
		this.tickets = tickets;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SaleTicketThread(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 30; i++){
			if(this.tickets > 0){
				System.out.println(this.name + ": 正在售票...剩余：" + this.tickets--);
			}
		}
	}

}
