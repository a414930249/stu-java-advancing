package cn.hankchan.test.others;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@org.junit.Test
	public void test2() {
		
	}

	public void test() {
		List<Records> records = new ArrayList<>();
		Records record1 = new Records("20161223", "0", "6");
		Records record2 = new Records("20161223", "6", "12");
		Records record3 = new Records("20161223", "12", "18");
		records.add(record1);
		records.add(record2);
		records.add(record3);
		List<String> forecastDatas = new ArrayList<>();
		forecastDatas.add("20161223");
		// Lambda

	}
}

class Records {
	private String forecastTime;
	private String startHour;
	private String endHour;

	public Records() {
	}

	public Records(String forecastTime, String startHour, String endHour) {
		super();
		this.forecastTime = forecastTime;
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public String getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(String forecastTime) {
		this.forecastTime = forecastTime;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	@Override
	public String toString() {
		return "Records [forecastTime=" + forecastTime + ", startHour=" + startHour + ", endHour=" + endHour + "]";
	}
}
