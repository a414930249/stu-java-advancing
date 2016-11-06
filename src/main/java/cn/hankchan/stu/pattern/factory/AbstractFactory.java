package cn.hankchan.stu.pattern.factory;

public abstract class AbstractFactory {

	abstract Color getColor(String color);
	
	abstract Shape getShape(String shape);
}
