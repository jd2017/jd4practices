package cn.com.jd.httpclient;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.com.jd.httpclient.test.HttpJSONObjectRequestUtilTest;
/**
 * @author jd.bai
 * @date 2016年12月5日
 * @time 下午2:13:37
 * @cookies: 获取目标cookies信息 及 遍历cookies信息
 */
public class Csrftoken {
	static Logger logger = Logger.getLogger(Csrftoken.class.getName());
	public static String getCsrfTokenAndCookie(String url){
		String cookie=null;
		try {
		//
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = client.execute(new HttpGet(url), context);
		CookieStore cookieStore = context.getCookieStore();
		List<Cookie> cookies = cookieStore.getCookies();
		Iterator<Cookie> cookiesItr = cookies.iterator();
		logger.info(cookies.toString());
		while(cookiesItr.hasNext()){
			Cookie cookieImp = cookiesItr.next();
			//获取目标cookie
			if(cookieImp.getName().equalsIgnoreCase("autofunCookie")){	
				cookie = cookieImp.getValue();
			}
		}
		httpResponse.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookie;
	}
	/**
	 * JSon Post  遍历返回的COOKies
	 * @param url
	 * @param jsonParam
	 * @param noNeedResponse
	 * @return JSONObject
	 */
	 public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){  
         
	        //post请求返回结果  
		 	HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClients.createDefault(); 
			
	        JSONObject jsonResult = null;  
	        HttpPost method = new HttpPost(url);  
	        try {  
	            if (null != jsonParam) {  
	                //解决中文乱码问题  
	                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");  
	                entity.setContentEncoding("UTF-8");  
	                entity.setContentType("application/json");  
	                method.setEntity(entity);  
	            }  
	            CloseableHttpResponse httpResponse = httpClient.execute(method,context);  
	            
	            url = URLDecoder.decode(url, "UTF-8");          
	            logger.info("json对象："+jsonParam+"\nurl:"+url+"\n");
	            CookieStore cookieStore = context.getCookieStore();
	    		List<Cookie> cookies = cookieStore.getCookies();
	    		Iterator<Cookie> cookiesItr = cookies.iterator();
//	    		System.out.println(cookies.toString());
	    		while(cookiesItr.hasNext()){
	    			Cookie cookieImp = cookiesItr.next();
	    			logger.info(cookieImp.toString());
	    			if(cookieImp.getName().equalsIgnoreCase("autofunCookie")){
	    				String autofunCookie = cookieImp.getValue();
	    				logger.info("Cookie:autofunCookie="+autofunCookie);
	    			}
	    		}
	            /**请求发送成功，并得到响应**/  
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {  
	                String strResult = "";  
	                try {  
	                    /**读取服务器返回过来的json字符串数据**/  
	                	strResult = EntityUtils.toString(httpResponse.getEntity(),"UTF-8"); 
	                    if (noNeedResponse) {
	                		//response 返回非空，状态码为200，非json数据时；
	                		 Map<String, String> status = new HashedMap();
	                     	status.put("status", "200");
	                     	jsonResult = JSONObject.fromObject(status);
	                         return jsonResult;  
	                     }
	                    /**把json字符串转换成json对象**/  
	                    jsonResult = JSONObject.fromObject(strResult);
	                } catch (Exception e) {  
	                	logger.info("post请求提交失败:" + url);
	                	e.printStackTrace();
	                }  
	            }
	            httpResponse.close();
	        } catch (IOException e) {  
	        	logger.info("post请求提交失败:" + url);
	        	e.printStackTrace();
	        }  
	        return jsonResult;  
	    }  
}
