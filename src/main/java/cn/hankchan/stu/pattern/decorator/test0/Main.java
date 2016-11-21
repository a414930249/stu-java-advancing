package cn.hankchan.stu.pattern.decorator.test0;

public class Main {

	public static void main(String[] args) {
		Beverage beverage = new DarkCoffee();
		double price = beverage.cost();
		System.out.println(price);
		OrangeCole cole = new OrangeCole();
		double colePrice = cole.cost();
		System.out.println(colePrice);
	}
}
