package cn.hankchan.stu.pattern.decorator.test0;

public class OrangeCole extends Beverage {

	@Override
	public double cost() {
		setSoy(true);
		setWhip(true);
		return super.cost();
	}
}
