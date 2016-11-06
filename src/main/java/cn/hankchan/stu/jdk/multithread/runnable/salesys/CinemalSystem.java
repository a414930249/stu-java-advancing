package cn.hankchan.stu.jdk.multithread.runnable.salesys;

import java.util.Map;

public class CinemalSystem implements Runnable {

	private Integer maps;
	public int getMaps() {
		return this.maps;
	}
	public void setMaps(int maps) {
		this.maps = maps;
	}
	
	@Override
	public void run() {
		synchronized (maps) {
			while(maps > 0) {
				System.out.println(Thread.currentThread().getName() + "售票中..." + maps);
				maps--;
			}
			System.out.println(Thread.currentThread().getName() + "很抱歉，售罄！");
		}
	}

}
