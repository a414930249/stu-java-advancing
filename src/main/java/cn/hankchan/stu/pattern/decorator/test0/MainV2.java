package cn.hankchan.stu.pattern.decorator.test0;

import java.util.ArrayList;
import java.util.List;

public class MainV2 {

	public static void main(String[] args) {
		BeverageV2 beverage = new BeverageV2();
		List<CondimentV2> condiments  = new ArrayList<>();
		condiments.add(new MilkV2());
		condiments.add(new WhipV2());
		condiments.add(new SoyV2());
		beverage.addCondiments(condiments);
		double price = beverage.cost();
		System.out.println(price);
	}
}
