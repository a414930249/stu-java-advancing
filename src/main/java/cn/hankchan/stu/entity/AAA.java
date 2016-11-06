package cn.hankchan.stu.entity;

import java.util.ArrayList;
import java.util.List;

public class AAA {

	private static List<TTT> aaa;
	public List<TTT> get(){
		return aaa;
	}
	@SuppressWarnings("static-access")
	public void set(List<TTT> aaa){
		this.aaa = aaa;
	}
	public List<TTT> toDoThings(){
		List<TTT> bbb = execute();
		aaa = bbb;
		System.out.println("before ToDoThings:" + bbb.toString());
		System.out.println("before AAA" + aaa.toString());
		change(bbb, 9);
		System.out.println("after ToDoThings:" + bbb.toString());
		System.out.println("after AAA" + aaa);
		return bbb;
	}
	private void change(List<TTT> bbb, int i) {
		TTT t = new TTT();
		t.setA(i);
		bbb.clear();
		bbb.add(t);
	}
	private List<TTT> execute() {
		List<TTT> ttt = new ArrayList<>();
		TTT t = new TTT();
		t.setA(100);
		ttt.add(t);
		return ttt;
	}
	public static void main(String[] args) {
		AAA AAAs = new AAA();
		List<TTT> B = AAAs.toDoThings();
		List<TTT> A = AAAs.get();
		System.out.println(A.equals(B));
		System.out.println(A == B);
	}
}
