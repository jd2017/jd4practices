package cn.com.jd.httpclient;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
/**
 * 
 * @author jd.bai
 * @date 2016年12月6日
 * @time 下午5:15:27
 */
public class HttpJSONObjectRequestUtil {
	static Logger logger = Logger.getLogger(HttpJSONObjectRequestUtil.class.getName());
	/** 
     * 发送get请求 
     * @param url 路径 
     * @return 
     */  
	public static JSONObject httpGet(String url){
        
		//get请求返回结果  
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
        JSONObject jsonResult = null;  
        try {   
            //发送get请求  
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse getResponse = httpClient.execute(httpGet,context);
            
            /**请求发送成功，并得到响应**/  
            if (getResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                /**读取服务器返回过来的json字符串数据**/  
                String strResult = EntityUtils.toString(getResponse.getEntity());
                if(strResult.equals("")){
                	//getResponse 状态码为200，非json对象时；
					Map<String, String> status = new HashedMap();
                	status.put("status", "SUCCESS");
                	jsonResult = JSONObject.fromObject(status);
                	return jsonResult; 
                }
                /**把json字符串转换成json对象**/  
                jsonResult = JSONObject.fromObject(strResult);
//              url = URLDecoder.decode(url, "UTF-8");
            } else {  
            	logger.info("get请求提交失败:" + url);  
            }  
        } catch (IOException e) {  
        	logger.info("get请求提交失败:" + url);
        	e.printStackTrace();
        }  
        return jsonResult;  
    }     
    /** 
     * httpPost 
     * @param url  路径 
     * @param jsonParam 参数 
     * @return 
     */  
    public static JSONObject httpPost(String url,JSONObject jsonParam){  
        return httpPost(url, jsonParam, false);  
    }    
    /** 
     * post请求 
     * @param url         url地址 
     * @param jsonParam     参数 
     * @param noNeedResponse    不需要返回结果 
     * @return 
     */  
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
    	
    	 @SuppressWarnings("deprecation")
		DefaultHttpClient httpClient =new DefaultHttpClient(); 
         //请求返回结果
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
            HttpResponse response = httpClient.execute(method);  
            url = URLDecoder.decode(url, "UTF-8");          
            logger.info("json对象："+jsonParam+"\nurl:"+url);
            int statusCode = response.getStatusLine().getStatusCode();
            
            /**请求发送成功，并得到响应**/  
            if ( statusCode== 200) {  
                String strResult = "";  
                try {  
                    /**读取服务器返回过来的json字符串数据**/  
                	strResult = EntityUtils.toString(response.getEntity(),"UTF-8"); 
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
                	e.printStackTrace();
                }  
            }else{
            	new HttpException("返回状态码Status："+statusCode);
            }
        } catch (IOException e) {  
        	System.out.println("post请求提交失败:" + url+"请检查URL链接及数据提交方式");
        	e.printStackTrace();
        }  
        return jsonResult;  
    }
}
