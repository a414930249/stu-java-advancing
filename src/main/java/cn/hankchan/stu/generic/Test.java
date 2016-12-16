package cn.hankchan.stu.generic;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@SuppressWarnings("static-access")
	@org.junit.Test
	public void test() {
		GenericApplyImpl impl = new GenericApplyImpl();
		List<String> lists = new ArrayList<>();
		GenericApplyUtil applyUtil = new GenericApplyUtil();
		List<String> result = applyUtil.get(lists, impl);
		System.out.println(result);
	}
}
