package cn.hankchan.stu.pattern.observer;

import java.util.ArrayList;

/**
 * 观察者模式主题接口的实现
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 14 Nov 2016-09:09:45
 * @detail 根据需求，拥有一个Observer列表
 */
public class WeatherData implements WeatherDataSubject {

	public WeatherData() {
		observers = new ArrayList<>();
	}
	/** 注册的Observer列表 */
	ArrayList<WeatherDataObserver> observers;
	
	public float pressures;
	public float temperature;
	public float humidity;
	/**
	 * 用于模拟数据改变。当数据变化时，调用notifyObserver()方法通知所有Observer对象 
	 */
	public void setParam(float temperature, float humidity, float pressures) {
		this.pressures = pressures;
		this.temperature = temperature;
		this.humidity = humidity;
		changed();
	}
	public void changed() {
		notifyObserver();
	}
	@Override
	public void notifyObserver() {
		for(WeatherDataObserver observer : observers) {
			observer.update(temperature, humidity, pressures);
		}
	}
	@Override
	public void registObserver(WeatherDataObserver observer) {
		// 经过测试可得出：必须做出判断，否则出现数据重叠（详见包 *.observer.test 中的代码）
		if(!observers.contains(observer)) {
			observers.add(observer);	
		}
		//observers.add(observer);
	}
	@Override
	public void removeObserver(WeatherDataObserver observer) {
		if(observers.contains(observer)) {
			observers.remove(observer);
		}
	}
	
	public String getTemperature() {
		System.out.println("get Temperature from APIs");
		return String.valueOf(Math.random());
	}
	public String getHumidity() {
		System.out.println("get Humidity from APIs");
		return String.valueOf(Math.random());
	}
	public String getPressures() {
		System.out.println("get Pressures from APIs");
		return String.valueOf(Math.random());
	}

}
