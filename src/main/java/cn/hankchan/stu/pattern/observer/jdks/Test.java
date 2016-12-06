package cn.hankchan.stu.pattern.observer.jdks;

import java.util.Date;

import cn.hankchan.stu.util.TimeUtils;

public class Test {

	@org.junit.Test
	public void test2() throws InterruptedException {
		System.out.println("running.....");
		WeatherData weatherData = new WeatherData();
		@SuppressWarnings("unused")
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		ObserverResult observerResult = new ObserverResult();
		System.out.println("changing....after 2 seconds");
		Thread.sleep(2000);
		observerResult.setTime(TimeUtils.YYYYMMDDHHMM.format(new Date()));
		// changed now.
		weatherData.setParam(observerResult);
		System.out.println("prepare to registe other Observer...");
		Thread.sleep(2000);
		FirstDisplayer firstDisplayer = new FirstDisplayer(weatherData);
		weatherData.addObserver(firstDisplayer);
		observerResult.setTime(TimeUtils.ISO8601.format(new Date()));
		weatherData.setParam(observerResult);
	}
	
	@org.junit.Test
	public void test() {
		WeatherData weatherData = new WeatherData();
		FirstDisplayer firstDisplayer = new FirstDisplayer(weatherData);
		ObserverResult observerResult = new ObserverResult();
		observerResult.setName("Hello World");
		for(int i = 0; i < 10; i++) {
			observerResult.setTime(TimeUtils.ISO8601.format(new Date()));
			// changed now.
			weatherData.setParam(observerResult);
			if(i == 4) {
				weatherData.deleteObserver(firstDisplayer);
			}
		}
	}
}
