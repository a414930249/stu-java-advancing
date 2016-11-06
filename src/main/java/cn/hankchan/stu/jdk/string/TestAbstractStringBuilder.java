package cn.hankchan.stu.jdk.string;

import org.junit.Test;

import cn.hankchan.stu.entity.AAA;

public class TestAbstractStringBuilder {

	@Test
	public void testAppend() {
		//String没法直接转成StringBuilder。。
		StringBuilder sbabc = new StringBuilder("sbabc");
		System.out.println(String.valueOf(sbabc));
		
		StringBuilder s1 = new StringBuilder("hello world..");
//		s1.reverse();
		System.out.println(s1);
		System.out.println(s1.reverse());
		System.out.println("-----------------------");
		StringBuilder strb = new StringBuilder("abdcdefg");
		System.out.println(strb.lastIndexOf("d"));
		System.out.println(strb.indexOf("d"));
		System.out.println(strb.indexOf("de", 3));
		System.out.println("abceeeabcffabc".indexOf("abc"));
		System.out.println("abceeeabcffabc".lastIndexOf("abc"));
		System.out.println("-----------------------");
		int i = 0;
		char[] ch = new char[3];
		ch[i++] = 'a';
		ch[i++] = 'b';
		System.out.println("ch: " + ch[i]);
		int j = 0;
		System.out.println("j = 0, sysout(++j): " + ++j);
		int k = 0;
		System.out.println("k = 0, sysout(k++): " + k++);
		System.out.println("-----------------------");
		String str = "";
		System.out.println("[String str = ''] : " + str);
		str = null;
		System.out.println("[str = null] : " + str);
		str += str;
		System.out.println("[str += str] : " + str);
		System.out.println("=============");
		StringBuilder builder = new StringBuilder("abcdefg");
		String str2 = new String();
		str2 = null;
		AAA aaa = null;
		System.out.println(" AAA aaa = null : " + aaa);
		System.out.println(str2);
		StringBuilder result = builder.append(str2);
		System.out.println(result);
		String str3 = "null";
		str3 += str3;
		System.out.println(str3);
		StringBuilder sb = new StringBuilder();
		sb.append(str2);
		System.out.println(sb);
	}
	
	@Test
	public void test() {
		StringBuilder builder = new StringBuilder("builder");
		String str = new String("string");
		System.out.println(builder);
		builder.append("abc");
		System.out.println(builder);
		System.out.println(str);
		System.out.println(String.join("=", "java", "not", "linux"));
		System.out.println(builder.capacity());
		System.out.println(str.codePointAt(4)); // "string".codePointAt(4) --> 110
		System.out.println((char)110); // --> n
		System.out.println(str.codePointBefore(4)); // --> 105
		System.out.println((char)105); // --> i
		String str2 = str.substring(0);
		System.out.println(str2);
		char[] chs = {'h', 'i', 'j', 'k', 'l'};
		str2.getChars(1, 4, chs, 0); // "string" 's [1] ~ [4] ("tri") replaces the "h, i, j, k, l" 's [0] ~ [3] ("h, i, j") and return the second object..
		System.out.println(chs);
	}
}
