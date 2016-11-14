package cn.hankchan.stu.pattern.observer.test;

/**
 * 主题接口 
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 10:14:18 - 14 Nov 2016
 * @detail
 */
public interface Subject {

	public void registeObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}
