package cn.hankchan.stu.guava.test;

import org.junit.Test;

import com.google.common.base.Preconditions;

import cn.hankchan.stu.entity.Account;

/**
 * 前置条件
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 23:09:43 - 21 Dec 2016
 * @detail 前置条件：让方法调用的前置条件判断更简单。
 * Guava在Preconditions类中提供了若干前置条件判断的实用方法
 */
public class PreconditionsTest {

	@Test
	public void testPreconditions() {
		Account account = new Account();
		Person person = new Person();
		person.setName("kengkeng");
		person.setAge("25");
		// 1. Preconditions.checkNotNull(T)：检查T是否为null，该方法返回T，如果为null，抛出空指针异常
		person.setAccount(Preconditions.checkNotNull(account));
		System.out.println(person);
		// 2. 检查boolean是否为true，用来检查传递给方法的参数。如果是false，抛出IllegalArgumentException。
		boolean flag = true;
		Preconditions.checkArgument(flag);
		Preconditions.checkState(flag);
	}
}
class Person {
	private String name;
	private String age;
	private Account account;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", account=" + account + "]";
	}
}
