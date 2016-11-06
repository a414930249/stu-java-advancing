package cn.hankchan.stu.queue;

public class ServiceProxy implements Service {

	private Service _service;
	private ActiveObject _active_object;
	
	public ServiceProxy() {
		_service = new ServiceImpl();
		_active_object = new ActiveObject();
	}
	
	@Override
	public void sayHello() {
		MethodRequest mr = new SayHello(_service);
		_active_object.enqueue(mr);
	}

}
