package cn.hankchan.stu.jdk.eight;

import java.util.Arrays;

import org.junit.Test;

/**
 * Java8 新特性尝试
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 10:56:20 - 13 Dec 2016
 * @detail Lambda表达式（也称为闭包）是整个Java 8发行版中最受期待的在Java语言层面上的改变，Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中），
 * 或者把代码看成数据：函数式程序员对这一概念非常熟悉。在JVM平台上的很多语言（Groovy，Scala，……）从一开始就有Lambda，但是Java程序员不得不使用毫无新意的匿名类来代替lambda。
 */
public class FirstTest {

	@Test
	public void testLambda() {
		Arrays.asList("a", "b", "c", "d", "e").forEach(e -> System.out.println(e));
	}
	
}
