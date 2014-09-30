package com.eric.rb.action;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eric.rb.consts.URLConsts;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.bean.EHttpResultType;
import com.eric.rb.http.handler.HttpHandler;
import com.eric.rb.log.LOG;
import com.eric.rb.utils.AlgorithmUtil;

/**
 * @FileName: Login1Action.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class Login1Action extends AbstractAction{

	public EHttpRequest onBuildRequest() {
		EHttpRequest request = new EHttpRequest();
		request.setUrl(
				MessageFormat.format(
						URLConsts.LOGIN_1_URL,
						context.getUser().getQqNum()+"",
						AlgorithmUtil.encrypt(
								Long.parseLong(context.getUser().getQqNum()), 
								context.getUser().getPassword(), 
								context.getUser().getVerifyCode()),
						context.getUser().getVerifyCode())
		);
		request.setHttpResultType(EHttpResultType.STRING);
		return request;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		return HttpHandler.get(request);
	}
	// ('0',
	public Object onParseResult(EHttpResponse response) {
		Pattern p =	Pattern.compile("\\('(\\d)','0',\\'(http://\\S*=[\\d])\\'");
		Matcher m = p.matcher(response.getStringResult());
		LOG.println("login setp.1 "+response.getStringResult());
		if(m.find())
			return m.group(2);
		else
			return m.group(1);
			
	}
}
