package cn.hankchan.stu.jdk.string;

import java.io.Serializable;

public class TestString {

	@org.junit.Test
	public void test2() {
		String str = new String("abcdefga");
		String prefix = new String("de");
		System.out.println(str.startsWith(prefix, 3));
		System.out.println(str.startsWith(prefix));
		System.out.println(str.indexOf("cd", 7));
		System.out.println(str.indexOf(97, 0));
		System.out.println((char)99);
		System.out.println(str.replace("abc", "cba"));  // abcdefga --> cbadefga
		System.out.println(str.replaceFirst("a", "x")); // abcdefga --> xbcdefga
		System.out.println(str.replaceAll("a", "y"));  // abcdefga --> ybcdefgy
		System.out.println(str.split("d")[0]);
		System.out.println();
		/*
		int[] a = new int[1000];
		for(int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 10 ) + i;
		}
		long start = System.currentTimeMillis();
		int n = a.length;
		int i = 0;
		while(n-- != 0) {
			System.out.println(a[i]);
			i++;
		}
		long mid = System.currentTimeMillis();
		int[] b = new int[1000];
		for(int k = 0; k < a.length; k++) {
			a[k] = (int) (Math.random() * 10 ) + k;
		}
		System.out.println("=================");
		long mid2 = System.currentTimeMillis();
		for(int j = 0; j < b.length; j++) {
			System.out.println(b[j]);
		}long end = System.currentTimeMillis();
		System.out.println("---------------");
		System.out.println((mid - start) + ":" + (end - mid2));
		*/
	}
	@org.junit.Test
	public void test1() {
		char[] a = new char[4];
		a[0] = 'a';
		a[1] = 'b';
		a[2] = 'c';
		a[3] = 'd';
		char[] b = new char[4];
		for(int i = 0; i < b.length; i++) {
			if(i > 2) {
				b[i] = 'z';
			} else {
				b[i] = 'x';
			}
		}
		String str = new String(a);
		System.out.println(str);
		str.getChars(0, 2, b, 2);
		System.out.println(b);
		boolean flag = str.contentEquals(new StringBuffer("abcd"));
		System.out.println(flag);
		str.equals("aaa");
		Str str2 = new Str(a);
		boolean flag2 = str2.equals("aaa");
		boolean flag3 = str2.equals(new Str(b));
		System.out.println(flag2);
		System.out.println(flag3);
	}

	
}
class Str implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private char[] value;
	private int length;
	
	public Str() {
		this.value = new char[0];
	}
	public Str(char[] a) {
		this.value = a;
		this.length = a.length;
	}
	public char charAt(int index) {
		if(index < 0 || index >= value.length )
			throw new StringIndexOutOfBoundsException(index);
		return value[index];
	}
	public boolean isEmpty() {
		return length == 0;
	}
	public int length() {
		return value.length;
	}
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Str) {
        	Str anotherString = (Str)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
}
