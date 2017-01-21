package cn.hankchan.stu.pattern.factory.test;

public class BeforeFactoryTest {

	// 1.最原始的方式
	Pizza orderPizza() {
		Pizza pizza = new Pizza();
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	// 2.为了系统更有弹性，使用接口替代具体的Pizza类
	Pizza2 orderPizza2(String type) {
		Pizza2 pizza = null;
		// 以下部分代码没有对修改封闭，就是说仍旧是随着业务变化而存在变化的那部分代码
		if("cheese".equals(type)) {
			 pizza = new CheesePizza();
		}
		if("greek".equals(type)) {
			pizza = new GreekPizza();
		}
		// 以下部分是不想改变的部分
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	// 3.但是，如果类型越来越多，就存在很多问题
	Pizza2 orderPizza3(String type) {
		Pizza2 pizza = null;
		// TODO 详见PizzaStore的实现
		return pizza;
	}
	// 4.接下来，如果利用SimplePizzaFactory，写出三种不同的工厂，分别是NYPizzaFactory、
	// ChicagoPizzaFactory、CaliforniaPizzaFactory等。。那么各地的披萨店都有自己的工厂可以使用
	public void test() {
		NYPizzaFactory nyPizzaFactory = new NYPizzaFactory();
		PizzaStore2 nyPizzaStore2 = new PizzaStore2(nyPizzaFactory);
		nyPizzaStore2.orderPizza("NYgreek");
	}
}
/** 4 */
class PizzaStore2 {
	PizzaFactory factory;
	public PizzaStore2(PizzaFactory factory) {
		this.factory = factory;
	}
	public Pizza2 orderPizza(String type) {
		Pizza2 pizza;
		pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
/** 4 */
class ChicagoPizzaFactory implements PizzaFactory {
	public Pizza2 createPizza(String type) {
		Pizza2 pizza = null;
		if("Chicagocheese".equals(type)) {
			 pizza = new CheesePizza();
		}
		if("Chicagogreek".equals(type)) {
			pizza = new GreekPizza();
		}
		return pizza;
	}
}
/** 4 */
class NYPizzaFactory implements PizzaFactory {
	public Pizza2 createPizza(String type) {
		Pizza2 pizza = null;
		if("NYcheese".equals(type)) {
			 pizza = new CheesePizza();
		}
		if("NYgreek".equals(type)) {
			pizza = new GreekPizza();
		}
		return pizza;
	}
}
/** 4 */
interface PizzaFactory {
	Pizza2 createPizza(String type); 
}
/** 3 */
class PizzaStore {
	SimplePizzaFactory factory;
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}
	public Pizza2 orderPizza(String type) {
		Pizza2 pizza;
		pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
/** 3 */
class SimplePizzaFactory {
	public Pizza2 createPizza(String type) {
		Pizza2 pizza = null;
		if("cheese".equals(type)) {
			 pizza = new CheesePizza();
		}
		if("greek".equals(type)) {
			pizza = new GreekPizza();
		}
		return pizza;
	}
}
/** 2 */
class GreekPizza implements Pizza2 {
	public void prepare() {
		System.out.println("prepare GreekPizza");
	}
	public void bake() {
		System.out.println("bake GreekPizza");
	}
	public void cut() {
		System.out.println("cut GreekPizza");
	}
	public void box() {
		System.out.println("box GreekPizza");
	}
}
/** 2 */
class CheesePizza implements Pizza2 {
	public void prepare() {
		System.out.println("prepare CheesePizza");
	}
	public void bake() {
		System.out.println("bake CheesePizza");
	}
	public void cut() {
		System.out.println("cut CheesePizza");
	}
	public void box() {
		System.out.println("box CheesePizza");
	}
}
interface Pizza2 {
	public void prepare();
	public void bake();
	public void cut();
	public void box();
}
/** 1 */
class Pizza {
	public void prepare() {
		System.out.println("prepare Pizza");
	}
	public void bake() {
		System.out.println("bake Pizza");
	}
	public void cut() {
		System.out.println("cut Pizza");
	}
	public void box() {
		System.out.println("box Pizza");
	}
}
