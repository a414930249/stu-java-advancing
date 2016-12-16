package cn.hankchan.stu.pattern.singleton;

/**
 * 单例模式
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 11 Nov 2016-09:25:12
 * @detail
 */
public class SingletonObject {
	
	private static class SingletonObjectHolder {
		private static SingletonObject instance = new SingletonObject();
	}
	
	// 防止被直接实例化
	private SingletonObject() { }
	
	public static SingletonObject getInstance() {
		return SingletonObjectHolder.instance;
	}
	
	public static void main(String[] args) {
		SingletonObject singletonObject = SingletonObject.getInstance();
		System.out.println(singletonObject);
	}
	
}
