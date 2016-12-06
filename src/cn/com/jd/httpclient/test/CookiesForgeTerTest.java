package cn.com.jd.httpclient.test;

import org.junit.Test;

import cn.com.jd.httpclient.CookiesForgeTer;

public class CookiesForgeTerTest {
	@Test
	public void test(){
		String url = "http://218.247.224.31/xcgj-app-ws/ws/0.1/trip/last";
		CookiesForgeTer.doHttpRequest1(url);
	}
}
