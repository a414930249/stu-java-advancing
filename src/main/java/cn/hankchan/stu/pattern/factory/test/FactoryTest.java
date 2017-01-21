package cn.hankchan.stu.pattern.factory.test;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void test() {
		// 首先选择一个店
		AbstractPizzaStore beijingStore = new BeijingStore();
		// 在这个店里下订单
		Pizza pizza = beijingStore.orderPizza("cheese");
		// 制作Pizza
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		// 成功
		System.out.println(pizza);
	}
}
