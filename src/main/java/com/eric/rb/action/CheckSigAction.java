package com.eric.rb.action;

import com.eric.rb.consts.KeyConsts;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.bean.EHttpResultType;
import com.eric.rb.http.handler.HttpHandler;

/**
 * @FileName: CheckSigAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月28日
 */
public class CheckSigAction extends AbstractAction{

	public EHttpRequest onBuildRequest() {
		EHttpRequest request = new EHttpRequest();
		request.setHttpResultType(EHttpResultType.STRING);
		request.setUrl((String)context.getParams().get(KeyConsts.PARAM));
		return request;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		return HttpHandler.get(request);
	}

	public Object onParseResult(EHttpResponse response) {
		return null;
	}

}
