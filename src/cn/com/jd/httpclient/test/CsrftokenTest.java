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
        JSONObject responseJSONObject= Csrftoken.httpPost(url,jsonParam,false);
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
		/**
		 * username=13522520336&password=6665faf97170007c5921f297ffa87ce7ce943191a384d5f263c78bbdee2d4a1c8d961722704404354f91749f100853921c589d65e4fcc32de2af9bf4e8dda9f4176e89d276a53a42681d40ae882ca9aace13d5c5d47f60d3ce6a3f32d8383dd2e83bf421a9783e9f38beda59352a96aef4d40f0f95ac1cb30884404810eb9bad
		 * &verifycode=&vcodestr=&action=login&u=https%253A%252F%252Fm.baidu.com%252Fusrprofile%253Fuid%253D1480672558841_358%2523logined&tpl=wimn&tn=&pu=&ssid=&from=&bd_page_type=&uid=1480672558841_358&type=&regtype=&subpro=wimn&adapter=0&skin=default_v2&regist_mode=&login_share_strategy=&client=&clientfrom=&connect=0&bindToSmsLogin=&isphone=&loginmerge=1&countrycode=&mobilenum=undefined&servertime=7363ab7e7e&gid=181A496-E6A1-48BF-9AA5-B2606D8EBCAF&logLoginType=wap_loginTouch
		 */
	@Test
	public void baiduLogin(){
		String url = "https://wappass.baidu.com/wp/api/login?tt=1480673370064";
		String username="13522520336";
		String password = "6665faf97170007c5921f297ffa87ce7ce943191a384d5f263c78bbdee2d4a1c8d961722704404354f91749f100853921c589d65e4fcc32de2af9bf4e8dda9f4176e89d276a53a42681d40ae882ca9aace13d5c5d47f60d3ce6a3f32d8383dd2e83bf421a9783e9f38beda59352a96aef4d40f0f95ac1cb30884404810eb9bad";
		User user = new User(username,password);
		
		JSONObject jsonParam = JSONObject.fromObject(user);
        JSONObject responseJSONObject = HttpJSONObjectRequestUtil.httpPost(url, jsonParam,true); 
        
        if(responseJSONObject !=null && "SUCCESS".equals(responseJSONObject.get("status"))){  
            JSONObject userStr = (JSONObject) responseJSONObject.get("user");  
            user = (User) JSONObject.toBean(userStr, User.class);  
              
            logger.info("http Post request process sucess");  
            logger.info("User:" + user+"-----StatusCode:"+responseJSONObject.get("status"));  
        }else {  
        	logger.info("http Post request process fail");  
        }  
		
	}
}
