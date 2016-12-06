package cn.hankchan.stu.pattern.observer.jdks;

public class ObserverResult {

	public String name;
	public String age;
	public String birth;
	public String city;
	public String phone;
	public String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "DataResult [name=" + name + ", age=" + age + ", birth=" + birth + ", city=" + city + ", phone=" + phone
				+ ", time=" + time + "]";
	}
	public ObserverResult() { }
	public ObserverResult(String name, String age, String birth, String city, String phone, String time) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
		this.city = city;
		this.phone = phone;
		this.time = time;
	}
	
}
