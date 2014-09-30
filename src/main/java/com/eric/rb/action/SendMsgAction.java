package com.eric.rb.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eric.rb.bean.EMessage;
import com.eric.rb.consts.ContextConsts;
import com.eric.rb.consts.KeyConsts;
import com.eric.rb.consts.URLConsts;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.handler.HttpHandler;
import com.eric.rb.log.LOG;

/**
 * @FileName: SendMsgAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class SendMsgAction extends AbstractAction{

	public EHttpRequest onBuildRequest() {
		
		EHttpRequest request = new EHttpRequest();
		
		JSONObject msg = new JSONObject();
		EMessage message = (EMessage)context.getParams().get(KeyConsts.PARAM);
		switch (message.getPollType()){
			case MESSAGE:
				request.setUrl(URLConsts.SEND_BUDDY_URL);
				msg.put("to", message.getFromUin());
				msg.put("face", 0);
				break;
			case GROUP_MESSAGE:
				request.setUrl(URLConsts.SEND_GROUP_URL);
				msg.put("group_uin", message.getFromUin());
				break;
		}
		JSONArray msgContent = new JSONArray();
		LOG.println("send message: " + message.getMsg());
		msgContent.add(message.getMsg());
		msgContent.add(message.getFont());
		
		msg.put("content", msgContent.toString());
		msg.put("msg_id", new Random().nextInt(9999999));
		msg.put("clientid", context.getSession().getClientId());
		msg.put("psessionid", context.getSession().getPsessionid());
		
		try {
			String content = URLEncoder.encode(msg.toString(), ContextConsts.CHARSET);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("r", content);
			params.put("clientid", context.getSession().getClientId()+"");
			params.put("psessionid", context.getSession().getPsessionid());
			
			request.setParams(params);
			return request;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		return HttpHandler.post(request);
	}

	public Object onParseResult(EHttpResponse response) {
		LOG.println("send over, result: "+response.getStringResult());
		return null;
	}

}
