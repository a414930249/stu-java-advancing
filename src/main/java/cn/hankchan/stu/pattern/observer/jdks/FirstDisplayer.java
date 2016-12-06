package cn.hankchan.stu.pattern.observer.jdks;

import java.util.Observable;
import java.util.Observer;

public class FirstDisplayer implements Display, Observer {

	Observable obserable;
	private ObserverResult result;
	private String a;
	private String b;
	private String c;
	
	public FirstDisplayer(Observable observable) {
		this.obserable = observable;
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) o;
			this.a = weatherData.getHumidity();
			this.b = weatherData.getPressures();
			this.c = weatherData.getTemperature();
			this.result = weatherData.observerResult;
			showData();
		}
	}

	@Override
	public void showData() {
		System.out.println("a:" + a);
		System.out.println("b:" + b);
		System.out.println("c:" + c);
		System.out.println("result:" + result);
	}

}
