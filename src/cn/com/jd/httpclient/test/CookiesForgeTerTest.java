package cn.com.jd.httpclient.test;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.com.jd.httpclient.CookiesForgeTer;
import cn.com.jd.httpclient.HttpClientUtil;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;
/**
 * @author jd.bai
 * @date 2016年12月8日
 * @time 下午5:08:46
 */
public class CookiesForgeTerTest {
	@Test
	public void test(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/trip/last";
		CookiesForgeTer.doHttpRequest1(url);
	}
	/**
	 * 请求人、车、设备信息：
	 */
	@Test
	public  void imeiAndPerson(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/user/relation";
		CookiesForgeTer.doHttpRequest1(url);
	}
	/**
	 * app端显示语言：
	 *	clientId:5450080dd95948abc4a6e330dc93d0ad
	 *	en-US、zh-CN
	 * 控制推送语言信息；
	 */
	@Test
	public void changeLanguage(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/oAuth/changeLanguage";
		User user =new User();
		user.setClientId("5450080dd95948abc4a6e330dc93d0ad");
		user.setClientType("0");
		user.setLanguage("zh-CN");
		
		JSONObject jsonParam = JSONObject.fromObject(user);
		JSONObject responseJSONObject = CookiesForgeTer.httpPostAndCookies(url, jsonParam,true);
		  if("200".equals(responseJSONObject.get("status"))){ 
			  System.out.println("success");
		  }else{
			  System.out.println("fail");
		  }
	}
	/**
	 * 请求：
	 * 	车辆系列列表、车辆车型列表、车辆车型列表
	 */
	@Test
	public void brands(){
		String all = "http://10.23.211.68/xcgj-app-ws/ws/0.1/bms/brands";
		String brandId ="http://10.23.211.68/xcgj-app-ws/ws/0.1/bms//models/v2?brandId=80";
		String modelId = "http://10.23.211.68/xcgj-app-ws/ws/0.1/bms/styles/v2?modelId=1300";
		 try {
			CookiesForgeTer.doHttpRequest1(all);
			CookiesForgeTer.doHttpRequest1(brandId);
			CookiesForgeTer.doHttpRequest1(modelId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 请求车辆位置信息
	 */
	@Test
	public void location(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/vehicle/location";
		CookiesForgeTer.doHttpRequest1(url);
	}
	/**
	 * 设置安防震动级别
	 */
	@Test
	public void shakeLevel(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/monitor/setting/shakeLevel";
		
	}
	/**
	 * 请求安防配置信息
	 */
	@Test
	public void setting(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/monitor/setting";
		CookiesForgeTer.doHttpRequest1(url);
	}
	/**
	 * 设置防骚扰模式开关状态
	 */
	@Test
	public void remindSwitch(){
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/monitor/setting/remind/switch";
		Map<String,String> map = new HashMap<String,String>();
		map.put("remindSwitch", "true");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.httpPostAndCookies(url, jsonParam, true);
		System.out.println(response);
		
	}
}
