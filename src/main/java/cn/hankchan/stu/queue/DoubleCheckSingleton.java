package cn.hankchan.stu.queue;

/**
 * 使用 DoubleCheck 方式实现单例测试
 * 需要两次校验是否已经实例化，第二次加锁
 * 如果没有第二次校验，则单例实现失败！
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 6 Aug 201612:40:19
 * <p>类说明:
 */
public class DoubleCheckSingleton {

	private static DoubleCheckSingleton dcs;
	
	public static DoubleCheckSingleton getDbChkSingleton() {
		if(dcs == null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DoubleCheckSingleton.class) {
				if(dcs == null) {
					dcs = new DoubleCheckSingleton();
				}
			}
		}
		return dcs;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDbChkSingleton().hashCode());
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDbChkSingleton().hashCode());
			}
		}, "t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDbChkSingleton().hashCode());
			}
		}, "t3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}
