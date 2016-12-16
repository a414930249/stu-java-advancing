package cn.hankchan.stu.generic;

public class GenericApplyImpl implements GenericApply<String, Integer> {

	@Override
	public Integer apply(String t) {
		return Integer.parseInt(t);
	}

}
