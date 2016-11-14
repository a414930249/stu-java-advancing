package cn.hankchan.stu.pattern.observer;

public class SecondDisplay implements Display, WeatherDataObserver {

	WeatherDataSubject weatherData;
	public float temp;
	public float press;
	public float huminity;
	public SecondDisplay(WeatherDataSubject weatherData) {
		this.weatherData = weatherData;
	}
	@Override
	public void showData() {
		System.out.println("update...." + temp + ", " + press + ", " + huminity);
	}

	@Override
	public void update(float temp, float huminity, float press) {
		this.temp = temp;
		this.huminity = huminity;
		this.press = press;
		showData();
	}

}
