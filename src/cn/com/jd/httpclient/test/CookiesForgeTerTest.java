package cn.com.jd.httpclient.test;

import org.junit.Test;

import cn.com.jd.httpclient.CookiesForgeTer;

public class CookiesForgeTerTest {
	@Test
	public void test(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/trip/last";
		CookiesForgeTer.doHttpRequest1(url);
	}
}
