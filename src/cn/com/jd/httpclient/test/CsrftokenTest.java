package cn.com.jd.httpclient.test;

import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.com.jd.alog2struc.algorithms.SortDemo;
import cn.com.jd.httpclient.Csrftoken;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;

public class CsrftokenTest {
	static Logger logger = Logger.getLogger(CsrftokenTest.class.getName());
	String host =  new SortDemo().readTxtFile(this.getClass().getResource(".").getPath()+"host.txt");
	/**
	 * Post JSONObject 获取Cookies 值；
	 */
	@Test
	public void logginJSON() {
//		String url = "http://app.carsmart.asia/xcgj-app-ws/ws/0.1/oAuth/login"; //43.252.36.57 app.carsmart.asia
		String url = "http://10.23.3.31/xcgj-app-ws/ws/0.1/user/login"; //10.23.3.31
		User user = new User();
//		user.setCountryCode("60");
		user.setUsername("18822220002");//18822220002 1883333900
		user.setPassword("e10adc3949ba59abbe56e057f20f883e");
		JSONObject jsonParam = JSONObject.fromObject(user);
		JSONObject responseJSONObject = Csrftoken
				.httpPost(url, jsonParam, true);
		// JSONObject responseJSONObject = HttpRequestUtil.httpPost(url,
		// jsonParam);
		if ("200".equals(responseJSONObject.get("status"))) {
			logger.info(responseJSONObject.toString());
			logger.info("http Post request process sucess");
		} else {
			logger.info("http Post request process fail");
		}
	}
	@Test
	public void pushRequst(){
		String url = "http://"+host+"/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=101&type=1";
		String cookie = Csrftoken.getCsrfTokenAndCookie(url);
		System.out.println(cookie);
	}

}
