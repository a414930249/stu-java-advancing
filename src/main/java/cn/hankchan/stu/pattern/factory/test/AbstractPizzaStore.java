package cn.hankchan.stu.pattern.factory.test;

/***
 * 现在Pizza是抽象的
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 00:20:01 - 19 Dec 2016
 * @detail 现在已经有一个PizzaStore作为超类，让每个域类型（NYPizzaStore、ChicagoPizzaStore）都继承这个类型，
 * 每个子类各自决定如何制造Pizza
 */
public abstract class AbstractPizzaStore {

	/**
	 * PizzaStore的子类在createPizza()方法中，处理对象的实例化
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
		// createPizza()方法从工厂对象中移到PizzaStore
		pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	/**
	 * 实例化Pizza的责任交给了一个方法，该方法就如同于一个工厂！
	 * 工厂方法是抽象的，依赖子类来处理对象的创建；
	 * 工厂方法必须返回一个产品，在超类定义的方法中，通常用到工厂方法的返回值；
	 * 工厂方法将关于超类的代码和子类对象创建代码解耦合了
	 */
	abstract Pizza createPizza(String type);
	
}
