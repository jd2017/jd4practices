package cn.com.jd.httpclient.test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;

import cn.com.jd.httpclient.HttpClientUtil;

public class HttpClientUtilTest {
	static Logger logger = Logger.getLogger(HttpClientUtilTest.class.getName());
	/*
	 * autofun app 接口推送测试；
	 */
	@Test
	public void test(){
		for(int i =1;i<6;i++){
			switch(i){
			case 1:
				for(int j = 1;j<=3;j++)
					getRequestTest(i, j);
				break;
			case 2:
				for(int j=1;j<=3;j++)
					getRequestTest(i, j);
				break;
			case 3:
				getRequestTest(i, 1);
				break;
			case 4:
				for(int j = 1;j<=2;j++)
					getRequestTest(i, j);
				break;
			case 5:
				getRequestTest(i, 5);
				break;
			default:
				System.out.println("no find,over");
			}
				
		}
	}
    public void getRequestTest(int catageoryKey,int index) {  
//    	int catageoryKey,int ...index
			String url = "http://10.23.211.68/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=10"+catageoryKey+"&type="+index;
//		String url = "http://10.23.211.68/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=101&type=1";
//        http://10.23.211.68/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=105&type=5
        try {  
            String str = HttpClientUtil.doGet2(url, "UTF-8");  
            if (str != null) {  
            	logger.info("http Get request result:" + str);  
            } else {  
            	logger.info("http Get request process fail");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    @Test
    public  void getRequestTest2() {  
//    	http://10.23.211.68/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=105&type=5
        String url = "http://www.baidu.com";  
        try {  
            String str = HttpClientUtil.doGet2(url, "UTF-8");  
            if (str != null) {  
            	logger.info("http Get request result:" + str);  
            } else {  
            	logger.info("http Get request process fail");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    // post 发送get请求测试； fail 请求方式不对
    @Test
    public void test3(){
    	String url = "http://10.23.211.68/xcgj-ws/ws/0.1/debug/push";
    	Map<String, Object> _params = new HashMap<String, Object>();  
        _params.put("userId", "1");  
        _params.put("catageoryKey", "10");
        _params.put("type", "101");
        String str = HttpClientUtil.doPost(url, _params, "UTF-8", true); 
        if (str != null) {  
        	logger.info("http Post request result:" + str);  
        } else {  
        	logger.info("http Post request process fail");  
        }  
    }
    //post request;  数据封装错误
    @Test
    public  void postRequestTest() {  
  
        String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/oAuth/login";  
  
        Map<String, Object> _params = new HashMap<String, Object>();  
        _params.put("countryCode", "86");  
        _params.put("username", "18833330000");
        _params.put("password", "e10adc3949ba59abbe56e057f20f883e");
        String str = HttpClientUtil.doPost(url, _params, "UTF-8", true);  
        if (str != null) {  
        	logger.info("http Post request result:" + str);  
        } else {  
        	logger.info("http Post request process fail");  
        }  
    }  
}
