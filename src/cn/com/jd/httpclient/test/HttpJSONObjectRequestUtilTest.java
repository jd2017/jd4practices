package cn.com.jd.httpclient.test;

import java.util.logging.Logger;

import org.junit.Test;

import net.sf.json.JSONObject;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;

public class HttpJSONObjectRequestUtilTest {
	static Logger logger = Logger.getLogger(HttpClientUtilTest.class.getName());
	
	@Test
    public  void getRequestTest() {
        String url = "http://218.247.224.31/xcgj-ws/ws/0.1/debug/push?userId=1&catageoryKey=105&type=5";  
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
    	 String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/oAuth/login";  
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
        
        if(responseJSONObject !=null && "200".equals(responseJSONObject.get("status"))){  
            JSONObject userStr = (JSONObject) responseJSONObject.get("user");  
            user = (User) JSONObject.toBean(userStr, User.class);  
              
            logger.info("http Post request process sucess");  
            logger.info("User:" + user+"-----StatusCode:"+responseJSONObject.get("status"));  
        }else {  
        	logger.info("http Post request process fail");  
        }  
    }  
}
