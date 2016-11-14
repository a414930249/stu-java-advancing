package cn.hankchan.stu.pattern.observer.test;

import java.util.ArrayList;
import java.util.Date;

import cn.hankchan.stu.util.TimeUtils;

public class WeatherData implements Subject {
	
	public WeatherData() {
		observers = new ArrayList<>();
	}
	/** 已注册的观察者列表 */
	private ArrayList<Observer> observers;
	/** 可变状态的数据 */
	private ObserverResult result = new ObserverResult();
	
	/** 用于模拟数据变化 */
	public void getResult() {
		System.out.println("start to changing now....");
		ObserverResult result = new ObserverResult();
		result = new ObserverResult("Beckham", "35", "1980-10-10", "London", "null", 
				TimeUtils.YYYYMMDDHHMMSSFFF.format(new Date()));
		this.result = result;
		notifyObservers();
	}
	
	@Override
	public void registeObserver(Observer observer) {
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if(observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers() {
		if(!observers.isEmpty()) {
			for(Observer observer : observers) {
				observer.update(result);
			}
		}
	}

}
