package cn.hankchan.test.pattern.observer;

/**
 * 观察者接口
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 01:37:34 - 11 Dec 2016
 * @detail 用于获得订阅的消息
 */
public interface Observer {

	public void update(SubjectMessages subjectMessages);
}
