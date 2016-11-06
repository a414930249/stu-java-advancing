package cn.hankchan.stu.jdk.string;

import java.io.Serializable;
import java.util.Comparator;

public final class MyString implements Serializable, Comparator<MyString> {

	private static final long serialVersionUID = 1L;
	private final char[] value;
	
	public MyString() {
		this.value = new char[0];
	}
	public int length() {
		return value.length;
	}
	@Override
	public int compare(MyString o1, MyString o2) {
		int len1 = o1.length();
		int len2 = o2.length();
		int lim = Math.min(len1, len2);
		char[] v1 = o1.value;
		char[] v2 = o2.value;
		for(int i = 0; i < lim; i++) {
			char c1 = v1[i];
			char c2 = v2[i];
			if(c1 != c2) {
				return c1 - c2;
			}
		}/*
		int k = 0;
		while (k < lim) {
			char c1 = v1[k];
			char c2 = v2[k];
			if(c1 != c2) {
				return c1 - c2;
			}
			k++;
		}*/
		return len1 - len2;
	}

}
