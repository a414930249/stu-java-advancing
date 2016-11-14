package cn.hankchan.stu.pattern.observer;

public class FirstDisplay implements Display, WeatherDataObserver {

	WeatherDataSubject weatherData;

	public float temperature;
	public float humidity;
	
	/** 构造器需要weatherData对象作为注册使用 */
	public FirstDisplay(WeatherDataSubject weatherData) {
		this.weatherData = weatherData;
		weatherData.registObserver(this);
	}
	
	@Override
	public void showData() {
		System.out.println("temperature: " + temperature + "; humidity: " + humidity);
	}

	@Override
	public void update(float temp, float huminity, float press) {
		this.temperature = temp;
		this.humidity = huminity;
		showData();
	}

}
