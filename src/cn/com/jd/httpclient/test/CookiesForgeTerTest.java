package cn.com.jd.httpclient.test;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.com.jd.alog2struc.algorithms.SortDemo;
import cn.com.jd.httpclient.CookiesForgeTer;
import cn.com.jd.httpclient.HttpClientUtil;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;
/**
 * @author jd.bai
 * @date 2016年12月8日
 * @time 下午5:08:46
 */
public class CookiesForgeTerTest {
	String host =  new SortDemo().readTxtFile(this.getClass().getResource(".").getPath()+"host.txt");
	
	@Test
	public void test(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/trip/last";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 请求人、车、设备信息：
	 */
	@Test
	public  void imeiAndPerson(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/user/relation";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * app端显示语言：
	 *	clientId:5450080dd95948abc4a6e330dc93d0ad
	 *	en-US、zh-CN
	 * 控制推送语言信息；
	 */
	@Test
	public void changeLanguage(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/oAuth/changeLanguage";
		User user =new User();
		user.setClientId("5450080dd95948abc4a6e330dc93d0ad");
		user.setClientType("0");
		user.setLanguage("zh-CN");
		
		JSONObject jsonParam = JSONObject.fromObject(user);
		JSONObject responseJSONObject = CookiesForgeTer.postJSONRequest(url, jsonParam,true);
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
		String all = "http://"+host+"/xcgj-app-ws/ws/0.1/bms/brands";
		String brandId ="http://"+host+"/xcgj-app-ws/ws/0.1/bms//models/v2?brandId=80";
		String modelId = "http://"+host+"/xcgj-app-ws/ws/0.1/bms/styles/v2?modelId=1300";
		 try {
			CookiesForgeTer.getdoHttpRequest(all);
			CookiesForgeTer.getdoHttpRequest(brandId);
			CookiesForgeTer.getdoHttpRequest(modelId);
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
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/vehicle/location";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 设置安防震动级别
	 */
	@Test
	public void shakeLevel(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/monitor/setting/shakeLevel";
		Map<String,String> map = new HashMap<String,String>();
		map.put("level", "3");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
		
	}
	/**
	 * 请求安防配置信息
	 */
	@Test
	public void setting(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/monitor/setting";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 设置防骚扰模式开关状态
	 */
	@Test
	public void remindSwitch(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/monitor/setting/remind/switch";
		Map<String,String> map = new HashMap<String,String>();
		map.put("remindSwitch", "true");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 设置防骚扰模式时间段
	 */
	@Test
	public void timing(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/monitor/setting/remind/timing";
		Map<String,String> map = new HashMap<String,String>();
		map.put("remindStartTime", "21:10");
		map.put("remindEndTime", "12:00");
		
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 发起全车诊断
	 * 功能失效；
	 */
	@Deprecated
	public void trigge(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/diagnosis/trigge";
		Map<String,String> map = new HashMap<String,String>();
		map.put("timeout", "1000");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 请求个人信息
	 */
	@Test
	public void getUser(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/user";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 修改个人信息
	 */
	@Test
	public void updataUser(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/user";
		Map<String,String> map = new HashMap<String,String>();
		map.put("nickname", "123456789012");
		map.put("email", "come_here@jd.cn");
		map.put("sex", "女");
		map.put("birthday", "2012-12-12");
		map.put("area", "1000");
		map.put("signature", "There is a bug；There There is no limit to the number of characters, the platform service");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
		
	}
}
