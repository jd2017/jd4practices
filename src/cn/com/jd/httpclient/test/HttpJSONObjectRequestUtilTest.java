package cn.com.jd.httpclient.test;

import java.util.logging.Logger;

import org.junit.Test;

import net.sf.json.JSONObject;
import cn.com.jd.alog2struc.algorithms.SortDemo;
import cn.com.jd.httpclient.CookiesForgeTer;
import cn.com.jd.httpclient.HttpClientUtil;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;

public class HttpJSONObjectRequestUtilTest {
	static Logger logger = Logger.getLogger(HttpClientUtilTest.class.getName());
	String host =  new SortDemo().readTxtFile(this.getClass().getResource(".").getPath()+"host.txt");
	@Test
    public  void getRequestTest() {
        String url = "http://"+host+"/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=105&type=5";  
        JSONObject jsonObject = HttpJSONObjectRequestUtil.httpGet(url);  
        if(jsonObject != null) {  
            String userName = (String) jsonObject.get("message");  
            logger.info("message:" + userName);
            logger.info("http Get request process success"); 
        }else {  
        	logger.info("http Get request process fail");  
        }  
    }  
	@Test
    public  void postRequestTest() {  
    	 String url = "http://"+host+"/xcgj-app-ws/ws/0.1/oAuth/login";  
    	 User user = new User();  
    	 /*
    	  * 18900000001
    	  * e10adc3949ba59abbe56e057f20f883e
    	  */
    	 user.setCountryCode("86");  
    	 user.setUsername("18833330000"); 
    	 user.setPassword("e10adc3949ba59abbe56e057f20f883e");
         JSONObject jsonParam = JSONObject.fromObject(user);

        JSONObject responseJSONObject = HttpJSONObjectRequestUtil.httpPost(url, jsonParam,true); 
        
        if("200".equals(responseJSONObject.get("status"))){  
            logger.info("http Post request process sucess");  
            logger.info("StatusCode:"+responseJSONObject.get("status"));  
        }else {  
        	logger.info("http Post request process fail");  
        }  
    }
}
