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
	/**
	 * 设置车辆基本信息
	 * post;
	 */
	@Test
	public void vehicle(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/vehicle";
		Map<String,String> map = new HashMap<String,String>();
		map.put("licensePlate", "赣0K317");
		map.put("ein", "943F001796");
		map.put("vin", "LS5A3ABR99A501759");
		map.put("boughtDate", "2012-12-12");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 请求车辆基本信息
	 * method：GET；
	 */
	@Test
	public void getVehicle(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/vehicle";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 请求最后一次行程信息
	 * method：GET；
	 */
	@Test
	public void trip_last(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/trip/last";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 请求单次行程信息
	 * method：GET；
	 */
	@Test
	public void trip_Id(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/trip/info?tripId=58298907e4b0607c735c7275";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 请求历史行程信息
	 * method：GET；
	 */
	@Test
	public void page(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/trip/page";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 删除历史行程信息
	 * method：post；
	 */
	@Test
	public void delete_trip(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/trip/delete";
		Map<String,String> map = new HashMap<String,String>();
		map.put("tripId", "582988f3e4b0607c735c7274");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 请求消息通知状态
	 * 	ignate：点火报警；
		displace：移位报警；
		shake：震动报警；
		level为震动灵敏度；
		fuel：油量报警
		lowFuel：油量报警阀值
		battery：电压报警；
		lowBattery：电压报警阀值
		trip:单次行程报告；
		weekReport:  每周数据报告；
		remindSwitch:勿扰模式开关;
		remindStartTime: 勿扰模式起始时间;
		remindEndTime: 勿扰模式结束时间;
	 * method：GET；
	 */
	@Test
	public void notification(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/monitor/setting/message/notification";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 请求电子围栏列表
	 * count：围栏数量；id:围栏编号；name: 围栏名称； radius: 围栏半径, 单位为米(m),；remind:进出围栏提醒(0:进入提醒,1: 离开围栏提醒, 2: 进入+离开围栏提醒), lat:纬度,lng: 经度, address: 围栏的位置描述, created: 围栏创建时间
		排序规则: 按照创建时间,升序排列, 最先创建的围栏, 在最前面.
	* method：GET；
	 */
	@Test
	public void fence_list(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/fence/list";
		CookiesForgeTer.getdoHttpRequest(url);
	}
	/**
	 * 添加电子围栏
	 * 参数说明: name: 围栏名称, radius: 围栏半径, 单位为米(m), 但省略单位, 
	 *  remind:进出围栏提醒(0:进入围栏提醒,1: 离开围栏提醒, 2: 进入+离开围栏提醒), 
	 *  lat:纬度,lng: 经,address: 围栏的位置描述
	 */
	@Test
	public void fence_add(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/fence/add";
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "test");
		map.put("radius", "20000");
		map.put("remind", "2");
		map.put("lat", "3.04320125571666");
		map.put("lng", "101.6869194433093");
		map.put("address", "Test Domain");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 修改电子围栏
	 * post
	 */
	@Test
	public void fence_update(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/fence/update";
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "11681");
		map.put("name", "test_$update");
		map.put("radius", "200000");
		map.put("remind", "1");
		map.put("lat", "3.04320125571666");
		map.put("lng", "101.6869194433093");
		map.put("address", "Test Domain");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
	/**
	 * 修改电子围栏
	 * 参数：{"id": xxx}		id: 围栏的id标识
	 * post
	 */
	@Test
	public void fence_delete(){
		String url = "http://"+host+"/xcgj-app-ws/ws/0.1/fence/delete";
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "11682");
		JSONObject jsonParam = JSONObject.fromObject(map);
		JSONObject response = CookiesForgeTer.postJSONRequest(url, jsonParam, true);
		System.out.println(response);
	}
}
