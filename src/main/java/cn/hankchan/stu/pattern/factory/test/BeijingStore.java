package cn.hankchan.stu.pattern.factory.test;

public class BeijingStore extends AbstractPizzaStore {

	@Override
	Pizza createPizza(String type) {
		Pizza pizza = null;
		if("cheese".equals(type)) {
			pizza = new BeijingCheesePizza();
		} else if("veggie".equals(type)) {
			pizza = new BeijingVeggiePizza();
		}
		return pizza;
	}

}
