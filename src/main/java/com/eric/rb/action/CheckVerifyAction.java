package com.eric.rb.action;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eric.rb.consts.KeyConsts;
import com.eric.rb.consts.URLConsts;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.handler.HttpHandler;

/**
 * @FileName: CheckVerifyAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class CheckVerifyAction extends AbstractAction{
	
	public EHttpRequest onBuildRequest() {
		EHttpRequest request = new EHttpRequest();
		request.setUrl(MessageFormat.format(URLConsts.CHECK_VERIFY_URL, context.getUser().getQqNum()+"", Math.random()));
		return request;
	}
	
	public EHttpResponse onSubmit(EHttpRequest request) {
		EHttpResponse response = HttpHandler.get(request);
		return response;
	}

	public Object onParseResult(EHttpResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		Pattern p = Pattern.compile("\\,\\'(!\\w{3})\\'");
		Matcher matcher = p.matcher(response.getStringResult());
		if(matcher.find()){
			result.put(KeyConsts.VERIFY_STATE, KeyConsts.VERIFY_STATE_VALID);
			result.put(KeyConsts.VERIFY_CODE, matcher.group(1));
		}else{
			result.put(KeyConsts.VERIFY_STATE, KeyConsts.VERIFY_STATE_INVALID);
		}
		return result;
	}

}
