package cn.hankchan.stu.pattern.singleton;

public class SingletonObject {
	
	private static class SingletonObjectHolder {
		private static SingletonObject instance = new SingletonObject();
	}
	
	// 防止被直接实例化
	private SingletonObject() { }
	
	public static SingletonObject getInstance() {
		return SingletonObjectHolder.instance;
	}
}
