package cn.hankchan.stu.alibaba;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliDayuTest {

	public static void main(String[] args) throws ApiException {
		String url = "http://gw.api.tbsandbox.com/router/rest";
		String appkey = "23433960";
		String secret = "cfd6792c840fd494511f4356e7eeb12f";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("阿里大于");
		req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");
		req.setRecNum("13000000000");
		req.setSmsTemplateCode("SMS_585014");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}
}
