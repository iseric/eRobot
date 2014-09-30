package com.eric.rb.http.handler;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import com.eric.rb.http.bean.EHttpCookie;
import com.eric.rb.http.bean.EHttpMethod;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.bean.EHttpResultType;
import com.eric.rb.log.LOG;

/**
 * @FileName: HttpHanndler.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public class HttpHandler{
	private static HttpHandler httpHandler = new HttpHandler();
	
	private HttpConnectionManager httpConnectionManager = null;

	private HttpClient httpClient = null;
	private HttpMethod httpMethod = null;
	
	/**
	 * post method
	 * @param request
	 * @return
	 */
	public static EHttpResponse post(EHttpRequest request){
		return getInstance().execute(request, EHttpMethod.POST);
	}
	
	/**
	 * get method
	 * @param request
	 * @return
	 */
	public static EHttpResponse get(EHttpRequest request){
		return getInstance().execute(request, EHttpMethod.GET);
	}
	
	private static HttpHandler getInstance(){
		return httpHandler;
	}
	
	private HttpHandler() {
		// 创建一个线程安全的HTTP连接池
		httpConnectionManager = new MultiThreadedHttpConnectionManager();

		httpConnectionManager.getParams().setDefaultMaxConnectionsPerHost(30);
		httpConnectionManager.getParams().setMaxTotalConnections(80);
		
		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(httpConnectionManager);

		ict.start();
		
		initHttpClient();
	}
	private void initHttpClient(){
		httpClient = new HttpClient(httpConnectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
		httpClient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
	}
	
	@SuppressWarnings("deprecation")
	private EHttpResponse execute(EHttpRequest request, EHttpMethod method) {
		LOG.println(request.getUrl());
		if (method.equals(EHttpMethod.POST)){
			httpMethod = postMethod(request);
		}else{
			httpMethod = getMethod(request);
		}
		
		httpMethod.setFollowRedirects(false);

		// fill full http header
		this.fillHeader(httpMethod, request.getHeaders());
		
		try {
			httpClient.executeMethod(httpMethod);

			EHttpCookie.putCookies(httpMethod.getResponseHeaders());
			

			EHttpResponse response = new EHttpResponse();
			
			if (request.getHttpResultType().equals(EHttpResultType.STRING))
				response.setStringResult(httpMethod.getResponseBodyAsString());
			else if (request.getHttpResultType().equals(EHttpResultType.BYTES))
				response.setByteResult(httpMethod.getResponseBody());
			else if (request.getHttpResultType().equals(EHttpResultType.STREAM))
				response.setStreamResult(httpMethod.getResponseBodyAsStream());
			
//			EHttpCookie.printAllCookies();
			
			return response;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			/* 无论执行的方法或是否有异常被抛出
			 * 对于每一个HttpClient.executeMethod方法必须有一个method.releaseConnection来释放连接.
			 * */
			httpMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * method.setRequestBody(NameValuePair[] params)
	 * 使用重载方法NameValuePair[]参数服务端返500
	 * 
	 * @param request
	 * @return
	 */
	private HttpMethod postMethod(EHttpRequest request){
		PostMethod method = new PostMethod(request.getUrl());
		method.setRequestBody(request.getParamsForString());
//		method.setRequestBody(request.getParams());
		return method;
	}
	
	private HttpMethod getMethod(EHttpRequest request){
		GetMethod method = new GetMethod(request.getUrl());
		return method;
	}
	
	private void fillHeader(HttpMethod httpMethod, Header[] headers){
		if(headers == null) return;
		for(Header header : headers){
			httpMethod.setRequestHeader(header);
		}
	}
}
