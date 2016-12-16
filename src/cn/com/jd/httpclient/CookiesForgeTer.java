package cn.com.jd.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.text.DateFormatter;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
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
 * @Cookies信息，携带带cookies信息的请求设置；
 */
public class CookiesForgeTer {
	static Logger logger = Logger.getLogger(CookiesForgeTer.class.getName());
	static String host = "10.23.3.31"; // 10.23.3.31 10.23.211.68
	
	private static BasicClientCookie setCookies(String name, String value,
			String date){
		BasicClientCookie2 cookie = new BasicClientCookie2(name, value);
		cookie.setDomain(host);
		cookie.setPath("/");
		if (StringUtils.isNotBlank(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time;
			try {
				time = sdf.parse(date);
				cookie.setExpiryDate(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			cookie.setExpiryDate(null);
		}
		return cookie;
	}
	@Deprecated
	public static void getHttpRequest(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter("http.protocol.cookie-policy",
				CookiePolicy.BROWSER_COMPATIBILITY);
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 1000 * 60 * 10);

		DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(
				3, true);
		HttpContext localContext = new BasicHttpContext();
		// HttpRequest request2 = (HttpRequest)
		// localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
		httpClient.setHttpRequestRetryHandler(dhr);
		BasicCookieStore cookieStore = new BasicCookieStore();
		/**
		 * 如果此处cookies的key和value是用户正在登陆认证cookies，则请求url时addCookie带上
		 * 此cookies,则相当于带上了登陆的密钥，请求url可以不受登陆权限的限制，进行敏感的信息的操作。 例如：
		 * 对于简单的网站，我们可以根据这个思路写一个抢购的逻辑，通过机器快速下单， 而无需在网页上手工点击。
		 */
		cookieStore.addCookie(setCookies("autofunCookie",
				"a48086b7-34c9-48c3-a5b6-6873277497a0", null)); // ok
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		HttpGet request = new HttpGet();
		try {
			request.setURI(URI.create(url));
			HttpResponse response = null;
			response = httpClient.execute(request, localContext);
			logger.info("执行结果返回值："
					+ EntityUtils.toString(response.getEntity(), "utf-8"));
			// Thread.sleep(10l);
		} catch (Exception e) {
			logger.info("发生异常：" + e);
		}
	}

	/**
	 * doGetRequest 优化
	 * time: 添加的日期要大于当前日期；
	 * @param url
	 */
	public static void getdoHttpRequest(String url) {
		// HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 设置cookies
		HttpContext localContext = new BasicHttpContext();
		BasicCookieStore cookieStore = new BasicCookieStore();
		cookieStore.addCookie(setCookies("cdtAppOrMicroCookie",
				"df5f3ece-4710-4759-a71b-4c4a5d0cd8b9", "2016-12-15")); //[name: cdtAppOrMicroCookie][value: c2dfde34-4b15-49f5-af41-0b1b4a56b7f8]
		localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		HttpGet method = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(method,
					localContext);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				logger.info("执行结果返回值："
						+ EntityUtils.toString(response.getEntity(), "utf-8"));
			else
				logger.info("执行结果返回值："+ EntityUtils.toString(response.getEntity())+"HttpStatus:"
						+ response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			logger.info("发生异常：" + e);
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * 添加登录缓存的JSONObject 请求；
	 * time: 添加的日期要大于当前日期；
	 * noNeedResponse 不需要返回json数据
	 * 
	 */
	public static JSONObject postJSONRequest(String url,
			JSONObject jsonParam, boolean noNeedResponse) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(),
						"utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			logger.info("json对象：" + jsonParam + "\nurl:" + url);

			// 设置cookie内容；
			HttpContext localContext = new BasicHttpContext();
			BasicCookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(setCookies("autofunCookie",
					"a48086b7-34c9-48c3-a5b6-6873277497a0", "2016-12-14")); // ok[name: cdtAppOrMicroCookie][value: c2dfde34-4b15-49f5-af41-0b1b4a56b7f8]
			localContext.setAttribute(HttpClientContext.COOKIE_STORE,
					cookieStore);
			// 将cookies添加到 请求中去；
			CloseableHttpResponse responseResult = httpClient.execute(method,
					localContext);
			int statusCode = responseResult.getStatusLine().getStatusCode();
			String strResult = "";
			/** 请求发送成功，并得到响应 **/
			if (statusCode == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(responseResult.getEntity(),
						"UTF-8");
				if (noNeedResponse) {
					// response 返回非空，状态码为200，非json数据时；
					Map<String, String> status = new HashedMap();
					status.put("status", "200");
					jsonResult = JSONObject.fromObject(status);
					return jsonResult;
				}
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.fromObject(strResult);
			} else {
				strResult = EntityUtils.toString(responseResult.getEntity(),
						"UTF-8");
				logger.info("***response 失败内容***:" + strResult);
				logger.info("post请求提交失败:" + url + "请检查URL链接及数据提交方式"
						+ statusCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
}
