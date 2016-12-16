package cn.hankchan.test.pattern.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 气象数据主题
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 01:38:25 - 11 Dec 2016
 * @detail 作为一个主题，需要实现主题接口；需要提供订阅了该主题的观察者列表；
 */
public class WeatherDataSubject implements Subject {

	private List<Observer> observers;
	
	private SubjectMessages messages = new SubjectMessages();
	
	public WeatherDataSubject() {
		observers = new ArrayList<>();
	}
	
	public void changeMessages() {
		SubjectMessages messages= new SubjectMessages();
		messages.setContent(new Date().toString());
		this.messages = messages;
		notifyObserver();
	}
	
	@Override
	public void registryObserver(Observer observer) {
		if(!observers.contains(observer)) 
			observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if(observers.contains(observer))
			observers.remove(observer);
	}

	@Override
	public void notifyObserver() {
		if(!observers.isEmpty()) {
			for(Observer observer : observers) {
				observer.update(messages);
			}
		}
	}

}
