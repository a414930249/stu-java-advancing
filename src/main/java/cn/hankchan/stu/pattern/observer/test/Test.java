package cn.hankchan.stu.pattern.observer.test;

public class Test {

	@org.junit.Test
	public void test2() throws InterruptedException {
		WeatherData weatherData = new WeatherData();
		FirstDisplayer firstDisplayer = new FirstDisplayer();
		
		// 主题注册方式
		weatherData.registeObserver(firstDisplayer);
		
		for(int i = 0; i < 5; i++) {
			Thread.sleep(2000);
			// 数据变化
			weatherData.getResult();
		}
	}
	
	@org.junit.Test
	public void test() throws InterruptedException {
		WeatherData weatherData = new WeatherData();
		
		// 观察者构造器注册方式
		FirstDisplayer firstDisplayer = new FirstDisplayer(weatherData);
		
		for(int i = 0; i < 10; i++) {
			Thread.sleep(2000);
			// 数据变化
			weatherData.getResult();
			if(i == 3) {
				// 测试重复注册问题
				weatherData.registeObserver(firstDisplayer);
			}
			if(i == 6) {
				// 测试移除注册问题
				weatherData.removeObserver(firstDisplayer);
			}
		}
	}
}
