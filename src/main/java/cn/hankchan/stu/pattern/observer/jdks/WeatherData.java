package cn.hankchan.stu.pattern.observer.jdks;

import java.util.Observable;

/**
 * Java JDK的观察者模式
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 14 Nov 2016-09:09:45
 * @detail 
 */
public class WeatherData extends Observable {

	public ObserverResult observerResult;
	
	/**
	 * 用于模拟数据改变。当数据变化时，调用notifyObserver()方法通知所有Observer对象 
	 */
	public void setParam(ObserverResult observerResult) {
		this.observerResult = observerResult;
		changed();
	}
	public String getTemperature() {
		return (observerResult.getTime() + "; " + String.valueOf(Math.random()));
	}
	public String getHumidity() {
		return (observerResult.getTime() + "; " + String.valueOf(Math.random()));
	}
	public String getPressures() {
		return (observerResult.getTime() + "; " + String.valueOf(Math.random()));
	}
	public void changed() {
		// 调用Observable的方法
		setChanged();  // 调用notifyObservers()之前需要指示状态已经改变
		notifyObservers();  // 状态改变通知
	}

}
