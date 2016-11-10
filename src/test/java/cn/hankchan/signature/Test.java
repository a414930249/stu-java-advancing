package cn.hankchan.signature;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

import cn.hankchan.stu.util.TimeUtils;
//import ren.yaoyan.utils.SignatureUtils;

public class Test {

	public static void main(String[] args) {
		String signature = "";
		String strAccessKeyId = "f041e065ffc3d905";
		String strAccessKeySecret = "2a860b8a0f57419f";
		String nonce = UUID.randomUUID().toString();
		String timestamp = TimeUtils.YYYYMMDDHHMMSS.format(new Date());
		Map<String, String> parameter = new HashMap<>();
		parameter.put("accessKeyID", strAccessKeyId);
		parameter.put("dataCode", "OCF_3H");
		parameter.put("elements", "ME");
		parameter.put("format", "txt");
		parameter.put("time", "20161108080000");
		parameter.put("timestamp", timestamp);
		parameter.put("type", "L88");
		parameter.put("version", "1.0");
		parameter.put("signatureMethod", "HMAC-SHA1");
		parameter.put("signatureNonce", nonce);// uid
		// 使用两种方法进行parameter参数的生成，实际应用中只用一种即可。
		// 调用签名方法其中之一
		signature = firstMethod(strAccessKeyId, strAccessKeySecret, nonce, parameter);
		//signature = secondMethod(strAccessKeyId, strAccessKeySecret, nonce, parameter);
		String url = "http://pmcvs.weather.com.cn/pms_getPMSCFile.do?accessKeyID=" + strAccessKeyId
				+ "&dataCode=OCF_3H&elements=ME&format=txt&signature=" + signature + "&signatureMethod=HMAC-SHA1&signatureNonce=" + nonce
				+ "&time=20161108080000xtamp=" + timestamp + "&type=L88&version=1.0";
		System.out.println("=====>>>result: " + signature);
		System.out.println("=====>>>url: " + url);
	}
	/** 方法一：直接生成parameter参数 */
	public static String firstMethod(String strAccessKeyId, String strAccessKeySecret, 
			String nonce, Map<String, String> parameter) {
		String signature = "";
		try {
			signature = SignatureUtils.generate("GET", parameter, strAccessKeySecret);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(signature);
		return signature;
	}
	/** 方法二：使用url分解得到parameter参数的方式 */
	public static String secondMethod(String strAccessKeyId, String strAccessKeySecret, 
			String nonce, Map<String, String> parameter) {
		String signature = "";
		
		String url = "http://pmcvs.weather.com.cn//pms_getPMSCFile.do?accessKeyID=" + strAccessKeyId
				+ "&dataCode=LAPS3KM&elements=ME&format=gr2&signatureMethod=HMAC-SHA1&signatureNonce=" + nonce
				+ "&time=20160612120000&timestamp=20160614063752&type=L88&version=1.0";
		parameter.clear();
		try {
			parameter = SignatureUtils.splitQueryString(url);
			signature = SignatureUtils.generate("GET", parameter, strAccessKeySecret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(signature);
		return signature;
	}
}
