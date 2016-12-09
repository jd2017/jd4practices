package cn.com.jd.httpclient.test;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.junit.Test;
import cn.com.jd.httpclient.Csrftoken;
import cn.com.jd.httpclient.HttpJSONObjectRequestUtil;

public class CsrftokenTest {
	
	static Logger logger = Logger.getLogger(CsrftokenTest.class.getName());
	/**
	 *登录页面；获取Cookies 值；
	 */
		@Test
		public void test(){
		
		String url = "http://10.23.211.68/xcgj-app-ws/ws/0.1/oAuth/login";
		User user =new User();
		user.setCountryCode("86");  
   	 	user.setUsername("18833330000"); 
   	 	user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        JSONObject jsonParam = JSONObject.fromObject(user);
        JSONObject responseJSONObject= Csrftoken.httpPost(url,jsonParam,true);
//		 JSONObject responseJSONObject = HttpRequestUtil.httpPost(url, jsonParam); 
        if("200".equals(responseJSONObject.get("status"))|responseJSONObject !=null ){  
            JSONObject userStr = (JSONObject) responseJSONObject.get("user");  
            user = (User) JSONObject.toBean(userStr, User.class);  
            logger.info("http Post request process sucess");  
            logger.info("user:" + user);  
        }else {  
        	logger.info("http Post request process fail");  
        }  
	}
}
