package cn.hankchan.stu.guava.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Objects;

import cn.hankchan.stu.entity.Account;

/**
 * 常见Objects方法
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 00:04:47 - 22 Dec 2016
 * @detail 
 */
public class ObjectsTest {

	@Test
	public void test() {
		Account account = new Account();
		boolean flag = Objects.equal(null, account);
		System.out.println(flag);
		List<String> list = new ArrayList<>();
		list.add("Hello");
		Map<String, String> map = new HashMap<>();
		map.put("Key", "Value");
		System.out.println(((67f/89f) * 0.2 + (64f/72f) * 0.2 + (68f/118f) * 0.2)/0.8);
	}
}
