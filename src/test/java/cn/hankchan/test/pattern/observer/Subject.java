package cn.hankchan.test.pattern.observer;

/**
 * 主题接口
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 01:36:56 - 11 Dec 2016
 * @detail 用于被订阅，推送消息内容
 */
public interface Subject {

	public void registryObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void notifyObserver();
}
