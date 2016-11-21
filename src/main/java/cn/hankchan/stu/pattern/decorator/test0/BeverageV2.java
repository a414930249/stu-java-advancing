package cn.hankchan.stu.pattern.decorator.test0;

import java.util.ArrayList;
import java.util.List;

public class BeverageV2 {

	// 描述
	private String decription;
	// 底价
	private double price = 10;
	private List<CondimentV2> condiments;

	public BeverageV2() {
		condiments = new ArrayList<>();
	}
	public void addCondiments(List<CondimentV2> condiments) {
		this.condiments.addAll(condiments);
	}
	public double cost() {
		if(condiments.size() > 0) {
			for(CondimentV2 condiment : condiments) {
				price += condiment.cost();
			}
		}
		return price;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
}
