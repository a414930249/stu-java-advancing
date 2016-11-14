package cn.hankchan.stu.pattern.observer.test;

public class FirstDisplayer implements Displayer, Observer {

	WeatherData weatherData;
	
	public FirstDisplayer() { }
	
	public FirstDisplayer(WeatherData subject) {
		this.weatherData = subject;
		weatherData.registeObserver(this);
	}
	
	ObserverResult result;

	@Override
	public void display() {
		System.out.println("SOMETHING CHANGED====>>" + result);
	}

	@Override
	public void update(ObserverResult result) {
		this.result = result;
		display();
	}

}
