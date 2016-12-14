package cn.com.jd.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.Header;

/**
 * @author jd.bai
 * @date 2016年12月5日
 * @time 下午2:13:48
 * @Http 字符串的 get/post 请求方式；
 */
public class HttpClientUtil {
	static Logger logger = Logger.getLogger(HttpClientUtil.class.getName());

	/**
	 * httpClient的get请求方式
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String doGet1(String url, String charset) throws Exception {

		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		if (null == url || !url.startsWith("http")) {
			throw new Exception("请求地址格式不对");
		}
		// 设置请求的编码方式
		if (null != charset) {
			method.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=" + charset);
		} else {
			method.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=" + "utf-8");
		}
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {// 打印服务器返回的状态
			logger.info("Method failed: " + method.getStatusLine());
		}
		// 返回响应消息
		byte[] responseBody = method.getResponseBodyAsString().getBytes(
				method.getResponseCharSet());
		// 在返回响应消息使用编码(utf-8或gb2312)
		String response = new String(responseBody, "utf-8");
		logger.info("response:" + response);
		// 释放连接
		method.releaseConnection();
		return response;
	}

	/**
	 * httpClient的get请求方式2
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String doGet2(String url, String charset) throws Exception {
		/*
		 * 使用 GetMethod 来访问一个 URL 对应的网页, 实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
		 * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod
		 * 生成的Get方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
		 */
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("Method failed: " + getMethod.getStatusLine());
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				logger.info(h.getName() + "------------ " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			response = new String(responseBody, charset);
			logger.info("***response:***" + response);
			// 读取为 InputStream，在网页内容数据量大时候推荐使用
			// InputStream response = getMethod.getResponseBodyAsStream();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			logger.info("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url 请求的URL地址
	 * @param params 请求的查询参数,可以为null
	 * @param charset 字符集
	 * @param pretty 是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, Object> _params,
			String charset, boolean pretty) {

		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		// 设置Http Post数据
		if (_params != null) {
			for (Map.Entry<String, Object> entry : _params.entrySet()) {
				postMethod.setParameter(entry.getKey(),
						String.valueOf(entry.getValue()));
			}
		}
		try {
			int status = client.executeMethod(postMethod);

			if (status == HttpStatus.SC_OK) {
				// 读取为 InputStream，在网页内容数据量大时候推荐使用
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								postMethod.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty){
						response.append(line).append(
								System.getProperty("line.separator"));
					}else{
						response.append(line);
					}
				}
				reader.close();
			} else {
				throw new HttpException("执行HTTP Post请求数据方式或者数据封装有问题:"
						+ _params.toString() + url + "Status:" + status);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			postMethod.releaseConnection();
		}
		logger.info("******response******" + response.toString());
		return response.toString();
	}
}
