package cn.hankchan.stu.pattern.decorator.test0;

public class Beverage {

	private String description;
	private boolean milk;
	private boolean soy;
	private boolean mocha;
	private boolean whip;
	private boolean chocolate;
	private boolean kabchinote;
	
	public double cost() {
		double result = 12;
		if(isChocolate()) {
			result += 10;
		}
		if(isMilk()) {
			result += 9;
		}
		if(isKabchinote()) {
			result += 11;
		}
		if(isMocha()) {
			result += 5;
		}
		if(isSoy()) {
			result += 3;
		}
		if(isWhip()) {
			result += 2;
		}
		return result;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isMilk() {
		return milk;
	}
	public void setMilk(boolean milk) {
		this.milk = milk;
	}
	public boolean isSoy() {
		return soy;
	}
	public void setSoy(boolean soy) {
		this.soy = soy;
	}
	public boolean isMocha() {
		return mocha;
	}
	public void setMocha(boolean mocha) {
		this.mocha = mocha;
	}
	public boolean isWhip() {
		return whip;
	}
	public void setWhip(boolean whip) {
		this.whip = whip;
	}
	public boolean isChocolate() {
		return chocolate;
	}
	public void setChocolate(boolean chocolate) {
		this.chocolate = chocolate;
	}
	public boolean isKabchinote() {
		return kabchinote;
	}
	public void setKabchinote(boolean kabchinote) {
		this.kabchinote = kabchinote;
	}
	
}
