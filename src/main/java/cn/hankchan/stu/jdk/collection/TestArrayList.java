package cn.hankchan.stu.jdk.collection;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestArrayList {

	@Test
	public void test() {
		List<String> list = new ArrayList<>(3);
		System.out.println(list);
		List<String> list2 = new ArrayList<>();
		System.out.println(list2);
		List<List<Object>> list3 = new ArrayList<>();
		List<Object> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		list3.add(l1);
//		list3.add(l2);
		l1.add(new String());
		l1.add(12);
		l1.add('a');
		System.out.println(l1);
	}

}
