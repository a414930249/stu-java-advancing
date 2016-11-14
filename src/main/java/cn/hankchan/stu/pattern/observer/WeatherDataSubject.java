package cn.hankchan.stu.pattern.observer;

/**
 * 观察者模式的主题(Subject)
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 14 Nov 2016-09:12:02
 * @detail 包含一个观察者Observer列表，在构造器中实例化
 * 拥有添加Observer、删除Observer及通知Observer的方法。
 */
public interface WeatherDataSubject {

	/** 当数据变化时，通知Observer */
	public void notifyObserver();
	
	/** 注册 */
	public void registObserver(WeatherDataObserver observer);
	
	/** 移除注册 */
	public void removeObserver(WeatherDataObserver observer);
}
