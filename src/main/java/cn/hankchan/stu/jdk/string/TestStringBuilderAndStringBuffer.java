package cn.hankchan.stu.jdk.string;

import org.junit.Test;

public class TestStringBuilderAndStringBuffer {

	@Test
	public void test() {
		/**
		 * StringBuffer 是线程安全的，几乎所有的方法都使用 synchronized 修饰
		 * StringBuilder 不是线程安全的，效率较前者快
		 */
	}

}

/**
 * The type Abc cannot subclass the final class StringBuffer
 */
/*class Abc extends StringBuffer {
	
}*/

/**
 * The type Bcd cannot subclass the final class StringBuilder
 */
/*class Bcd extends StringBuilder {
	
}*/

