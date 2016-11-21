package cn.hankchan.stu.pattern.decorator.test0;

public class DarkCoffee extends Beverage {

	@Override
	public double cost() {
		setChocolate(true);
		setMilk(true);
		return super.cost();
	}
}
