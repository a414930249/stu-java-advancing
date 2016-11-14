package cn.hankchan.stu.pattern.observer;

public interface WeatherDataObserver {

	/** 当Subject的状态改变，将变化的值作为参数传递给Observer */
	public void update(float temp, float huminity, float press);
}
