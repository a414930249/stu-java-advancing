package cn.hankchan.test.pattern.observer;

public class FirstObserver implements DataPrintTable, Observer {

	SubjectMessages messages;

	WeatherDataSubject weatherDataSubject;
	
	public FirstObserver() { }
	public FirstObserver(WeatherDataSubject weatherDataSubject) {
		this.weatherDataSubject = weatherDataSubject;
		this.weatherDataSubject.registryObserver(this);
	}
	
	@Override
	public void update(SubjectMessages subjectMessages) {
		this.messages = subjectMessages;
		print();
	}

	@Override
	public void print() {
		System.out.println(this.getClass() + " 's Datas is Changing===>" + messages);
	}

}
