package cn.hankchan.stu.jdk.multithread.thread;

public class MyThread extends Thread {

	/**
	 * 优化  语句继续运行  的解决方法：
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		super.run();
		try {
			for(int i = 0; i < 500000; i++) {
				if(this.interrupted()) {
					System.out.println("已经停止状态！准备退出了！");
					throw new InterruptedException();
				}
				System.out.println("执行中, i = " + (i + 1));
			}
			System.out.println("我是在for循环之后的代码，现在你的this.interrupted是ture所以跳出循环执行了我。。");
			System.out.println("注意：===>>如果你看到了我，说明并非执行了interrupt()方法就可以让线程结束");
			
		} catch (InterruptedException e) {
			System.out.println("进到MyThread.java类run方法中的catch中了。。。");
			e.printStackTrace();
		}
	}
	
	/*@SuppressWarnings("static-access")
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 500000; i++) {
			if(this.interrupted()) {
				System.out.println("已经停止状态！准备退出了！");
				break;
			}
			System.out.println("执行中, i = " + (i + 1));
		}
		System.out.println("我是在for循环之后的代码，现在你的this.interrupted是ture所以跳出循环执行了我。。");
		System.out.println("注意：===>>如果你看到了我，说明并非执行了interrupt()方法就可以让线程结束");
	}*/
}
