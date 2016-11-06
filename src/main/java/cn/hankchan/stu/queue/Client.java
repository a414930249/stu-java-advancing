package cn.hankchan.stu.queue;

public class Client {

	private Service _service;
	
	public Client(Service s) {
		_service = s;
	}
	public void requestService() {
		_service.sayHello();
	}
}
