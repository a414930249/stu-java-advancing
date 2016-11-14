package cn.hankchan.stu.pattern.observer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		WeatherData weatherData = new WeatherData();
		@SuppressWarnings("unused")
		FirstDisplay firstDisplay = new FirstDisplay(weatherData);
		weatherData.setParam(25.1f, 100f, 250f);
		Thread.sleep(2000);
		System.out.println("change now..........................");
		weatherData.setParam(23.1f, 88f, 210f);
		Thread.sleep(2000);
		System.out.println("change now..........................");
		weatherData.setParam(30.1f, 900f, 190f);
		Thread.sleep(2000);
		weatherData.registObserver(new SecondDisplay(weatherData));
		weatherData.setParam(200f, 100f, 290f);
		Thread.sleep(2000);
		weatherData.setParam(33.5f, 90f, 110f);
	}
}
