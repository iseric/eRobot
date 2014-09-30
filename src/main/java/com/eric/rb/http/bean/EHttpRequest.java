package com.eric.rb.http.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;


/**
 * @FileName: EHttpRequest.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public class EHttpRequest {
	private String url;
	
	private EHttpResultType httpResultType = EHttpResultType.STRING;
	
	private Map<String, String> params = new HashMap<String, String>();
	
	public EHttpRequest(){
		EHttpHeader.putHread("Cookie", EHttpCookie.getCookies());
	}
	
	
	public NameValuePair[] getParams() {
        NameValuePair[] nameValuePair = new NameValuePair[params.size()];
        int i = 0;
        for (String key : params.keySet()) {
            nameValuePair[i] = new NameValuePair(key, params.get(key));
            i++;
        }
        return nameValuePair;
    }
	public String getParamsForString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
        for (String key : params.keySet()) {
        	sb.append(key);
        	sb.append("=");
        	sb.append(params.get(key));
        	i++;
        	if(i<params.size())
        		sb.append("&");
        	
        }
        return sb.toString();
    }
	
	public Header[] getHeaders() {
		Header[] headers = new Header[EHttpHeader.getHreads().size()];
		int i = 0;
		for (String key : EHttpHeader.getHreads().keySet()) {
			headers[i] = new Header(key, EHttpHeader.getHreads().get(key));
			i++;
		}
		return headers;
	}

	public EHttpResultType getHttpResultType() {
		return httpResultType;
	}
	public void setHttpResultType(EHttpResultType httpResultType) {
		this.httpResultType = httpResultType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
