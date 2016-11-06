package cn.hankchan.stu.jdk.multithread.runnable.salesys;

public class Test {

	@org.junit.Test
	public void testCinemalSystem() {
		CinemalSystem cinemalSystem = new CinemalSystem();
		cinemalSystem.setMaps(20);
		new Thread(cinemalSystem).start();
		new Thread(cinemalSystem).start();
		new Thread(cinemalSystem).start();
		new Thread(cinemalSystem).start();
		new Thread(cinemalSystem).start();
		new Thread(cinemalSystem).start();
	}
	
}
