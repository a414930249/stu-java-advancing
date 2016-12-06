package cn.hankchan.stu.pattern.observer.jdks;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, Display {

	Observable observable;
	private String currentPressure = "29.92f";
	private String lastPressure;
	
	public ForecastDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	@Override
	public void showData() {
		System.out.println(lastPressure);
		System.out.println(currentPressure);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData data = (WeatherData) o;
			lastPressure = currentPressure;
			currentPressure = data.getPressures();
			showData();
		}
	}

}
