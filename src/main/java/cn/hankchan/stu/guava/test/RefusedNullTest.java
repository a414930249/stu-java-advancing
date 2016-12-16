package cn.hankchan.stu.guava.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * 使用和避免Null
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 14:21:26 - 16 Dec 2016
 * @detail 轻率地使用null可能会导致很多令人惊愕的问题。通过学习Google底层代码库，我们发现95%的集合类不接受null值作为元素。
 * 我们认为， 相比默默地接受null，使用快速失败操作拒绝null值对开发者更有帮助。
 * 很多Guava工具类对Null值都采用快速失败操作，除非工具类本身提供了针对Null值的因变措施。
 * 此外，Guava还提供了很多工具类，让你更方便地用特定值替换Null值。
 */
public class RefusedNullTest {

	/**
	 * Guava用Optional<T>表示可能为null的T类型引用。一个Optional实例可能包含非null的引用（我们称之为引用存在），
	 * 也可能什么也不包括（称之为引用缺失）。它从不说包含的是null值，而是用存在或缺失来表示。但Optional从不会包含null值引用。
	 * <p>Thinking:<p>
	 * 使用Optional除了赋予null语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。
	 * Optional迫使你积极思考引用缺失的情况，因为你必须显式地从Optional获取引用。直接使用null很容易让人忘掉某些情形，
	 * 尽管FindBugs可以帮助查找null相关的问题，但是我们还是认为它并不能准确地定位问题根源。
	 * 如同输入参数，方法的返回值也可能是null。和其他人一样，你绝对很可能会忘记别人写的方法method(a,b)会返回一个null，
	 * 就好像当你实现method(a,b)时，也很可能忘记输入参数a可以为null。将方法的返回类型指定为Optional，也可以迫使调用者思考返回的引用缺失的情形。
	 */
	@Test
	public void testOptional() {
		// 使用Optional提供的方法，判断引用
		Optional<Integer> optional = Optional.of(5);  // 创建指定引用的Optional实例，若引用为null（引用缺失）则快速失败
		boolean flag = optional.isPresent();  // 如果Optional包含非null的引用（引用存在），返回true
		System.out.println(flag);
		Integer result = optional.get();  // 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
		System.out.println(result);
		
		Optional<String> optional3 = Optional.of("null");
		if(optional3.isPresent()) {
			System.out.println("字符串“null”引用存在==>>>>>" + optional3.get());
		} else {
			System.out.println("字符串“null”引用缺失！");
		}
		
		List<String> list = Arrays.asList("first", "second", "third");
		Optional<List<String>> optional2 = Optional.of(list);
		if(optional2.isPresent()) {
			System.out.println("引用存在==>>>>>" + list);
			System.out.println("引用存在==>>>>>" + optional2.get());
		} else {
			System.out.println("由于引用缺失，返回null值：" 
					+ optional2.orNull());  // 返回Optional所包含的引用，若引用缺失，返回null
			System.out.println("由于引用缺失，返回指定的值：" 
					+ optional2.or(Arrays.asList("first")));  // 返回Optional所包含的引用，若引用缺失，返回指定的值
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("a", "A");
		map.put("b", "B");
		map.put("k", "null");
		if(map.containsKey("k")) {
			System.out.println("获得k的值：" + map.get("k"));
		} else {
			System.out.println("nothing..");
		}
		
		Map<String, String> map2 = new HashMap<>();
		map2.put("x", "X");
		map2.put("y", "Y");
//		Optional<Map<String, String>> optional4 = Optional.fromNullable(map2);  // 创建指定引用的Optional实例，若引用为null则表示缺失
		Map<String, String> map3 = null;
		Optional<Map<String, String>> optional4 = Optional.fromNullable(map3);
		// 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
		Set<Map<String, String>> set = optional4.asSet();
		if(optional4.isPresent()) {
			System.out.println("引用存在：" + set);
		} else {
			System.out.println("引用缺失：" + set);
		}
		
		// 如同输入参数，方法的返回值也可能是null
		String getStr = getOptional(1);
		Optional<String> optional5 = Optional.fromNullable(getStr);  // 创建指定引用的Optional实例，若引用为null则表示缺失
		if(optional5.isPresent()) {
			System.out.println("该方法返回值：" + getStr);	
		} else {
			System.out.println("该方法返回的是null值！");
		}
		/**
		 * 综合上面的测试，可以知道：
		 * 1. Optional.of(T) 方法是当引用不存在就会快速失败（抛出一个空指针异常）的；
		 * 2. Optional.fromNullable(T) 方法创建的实例，引用为null表示缺失，不会执行快速失败，这很有用；
		 * 
		 * 3. Optional实例可以调用 isPresent() 方法判断引用是存在还是缺失；
		 * 
		 * 4. Optional实例可以调用 get()返回实例包含的引用；
		 * 5. Optional实例可以调用or()方法返回Optional所包含的引用，且如果该引用缺失，返回指定的值
		 * 6. Optional实例可以调用orNull()方法返回Optional所包含的引用，若引用缺失，返回null
		 */
		String abc = "Hello World";
		System.out.println("isNullOrEmpty()=>>" + Strings.isNullOrEmpty(abc));  // 如果字符串是空字符串或者引用为null，返回true，否则返回false
		System.out.println("emptyToNull()=>>" + Strings.emptyToNull(abc));  // 如果当前字符串不为空字符串，返回当前字符串，否则返回null
		System.out.println("nullToEmpty()=>>" + Strings.nullToEmpty(abc));  // 如果当前字符串不为null，返回当前字符串，否则返回空字符串
	}
	
	public static String getOptional(int i) {
		return i == 0 ? "hello world" : null;
	}
}
