package cn.hankchan.stu.entity;

public class Account {

	private int id;
	private String name;
	public Account() {
	}
	public Account(String name2) {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}
	
}
