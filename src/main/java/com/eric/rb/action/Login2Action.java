package com.eric.rb.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.eric.rb.consts.ContextConsts;
import com.eric.rb.consts.URLConsts;
import com.eric.rb.http.bean.EHttpCookie;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.handler.HttpHandler;
import com.eric.rb.log.LOG;

/**
 * @FileName: Login2Action.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class Login2Action extends AbstractAction{

	public EHttpRequest onBuildRequest() {
		EHttpRequest request = new EHttpRequest();
		
		request.setUrl(URLConsts.LOGIN_2_URL);
		try {
			JSONObject json = new JSONObject();
			json.put("status", "online");
			json.put("ptwebqq", EHttpCookie.getCookie("ptwebqq"));
			json.put("passwd_sig", "");
			json.put("clientid", context.getSession().getClientId());
			json.put("psessionid", "null");
			
			String content = "{\"status\":\"online\",\"ptwebqq\":\""
					+ EHttpCookie.getCookie("ptwebqq")
					+ "\",\"passwd_sig\":\"\",\"clientid\":\"" + context.getSession().getClientId()
					+ "\",\"psessionid\":null}";
			
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("r", URLEncoder.encode(content, ContextConsts.CHARSET));
			params.put("clientid", context.getSession().getClientId()+"");
			
			request.setParams(params);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return request;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		return HttpHandler.post(request);
	}

	public Object onParseResult(EHttpResponse response) {
		// 真实登陆, 从返回字符串中获取关键参数, 在很多消息推送中需要用到
		String responseResult = response.getStringResult();
		try {
			LOG.println("login setp.2 "+responseResult);
			JSONObject jsonObj = JSONObject.parseObject(responseResult);
//			JSONObject.parseObject(responseResult)
			Integer resultCode = jsonObj.getInteger(("retcode"));
			if(resultCode != null && resultCode == 0){
				JSONObject result = jsonObj.getJSONObject("result");
				context.getSession().setVfwebqq(result.getString("vfwebqq"));
				context.getSession().setPsessionid(result.getString("psessionid"));
				return true;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

}
