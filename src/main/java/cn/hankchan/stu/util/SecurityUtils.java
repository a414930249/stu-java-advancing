package cn.hankchan.stu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

	// 十六进制以下数字到字符的映射数组
	private static String[] hexDigits = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	
	public static String turnToMD5(String password) {
		try {
			// 创建具有指定算法名称的信息摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组对摘要更新，并完成摘要计算
			byte[] result = md.digest(password.getBytes());
			// 将得到的字节数组编成字符串返回
			String resultStr = byteArrayToHexString(result);
			return resultStr.toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** 将字节数组转化为十六进制的字符串 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < b.length; i++) {
			sb.append(byteArrayToHexString(b[i]));
		}
		return sb.toString();
	}
	/** 将字节转化为十六进制的字符串 */
	private static String byteArrayToHexString(byte b) {
		int n = b;
		if(n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n / 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
}
