package cn.com.jd.httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
/**
 * @author jd.bai
 * @date 2016年12月5日
 * @time 下午2:13:25
 */
public class CookiesForgeTer{
	static Logger logger = Logger.getLogger(CookiesForgeTer.class.getName());
	@SuppressWarnings("deprecation")
	private static BasicClientCookie setCookies(String name,String value,String date){ 
        BasicClientCookie2 cookie = new BasicClientCookie2(name,value); 
        cookie.setDomain("218.247.224.31"); 
        cookie.setPath("/"); 
        if (StringUtils.isNotBlank(date)) { 
            cookie.setExpiryDate(new Date(date)); 
        }else{ 
            cookie.setExpiryDate(null); 
        } 
        return cookie; 
    }
	@SuppressWarnings("deprecation")
	public static void doHttpRequest(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.protocol.cookie-policy", 
                CookiePolicy.BROWSER_COMPATIBILITY); 
        HttpParams params = httpClient.getParams(); 
        HttpConnectionParams.setConnectionTimeout(params, 3000); 
        HttpConnectionParams.setSoTimeout(params, 1000*60*10); 
		 
        DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(3,true); 
        HttpContext localContext = new BasicHttpContext(); 
        //HttpRequest request2 = (HttpRequest) localContext.getAttribute(ExecutionContext.HTTP_REQUEST); 
        httpClient.setHttpRequestRetryHandler(dhr);
        BasicCookieStore cookieStore = new BasicCookieStore();
        /**
         * 如果此处cookies的key和value是用户正在登陆认证cookies，则请求url时addCookie带上
         * 此cookies,则相当于带上了登陆的密钥，请求url可以不受登陆权限的限制，进行敏感的信息的操作。
         * 例如：
         * 对于简单的网站，我们可以根据这个思路写一个抢购的逻辑，通过机器快速下单，
         * 而无需在网页上手工点击。
         */
        cookieStore.addCookie(setCookies("autofunCookie", "a48086b7-34c9-48c3-a5b6-6873277497a0",null)); //ok 
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore); 
        HttpGet request = new HttpGet(); 
        try{
                request.setURI(URI.create(url));
                HttpResponse response = null;
                response = httpClient.execute(request, localContext);
                System.out.println("执行结果返回值："+EntityUtils.toString(response.getEntity(), "utf-8"));
//                Thread.sleep(10l);
        } catch (Exception e) {
            System.out.println("发生异常："+e);
        }
    }
	/**
	 * doHttpRequest 优化
	 * @param url
	 */
	public static void doHttpRequest1(String url){
//		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置cookies
        HttpContext localContext = new BasicHttpContext(); 
        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(setCookies("autofunCookie", "a48086b7-34c9-48c3-a5b6-6873277497a0",null)); //ok 
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore); 
        HttpGet method = new HttpGet(url); 
        try{
        	CloseableHttpResponse  response = httpClient.execute(method, localContext);
               logger.info("执行结果返回值："+EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch (Exception e) {
        		logger.info("发生异常："+e);
        }finally{
        	method.releaseConnection();
        }
    }
}
