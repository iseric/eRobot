package com.eric.rb.action;

import com.eric.rb.core.EAction;
import com.eric.rb.core.EContext;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;


/**
 * @FileName: AbstractAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public abstract class AbstractAction implements EAction{
	protected EContext context;
	
	public void init(EContext context){
		this.context = context;
	}
	
	/**
	 * action 核心处理类
	 */
	public final Object execute(){
		EHttpRequest request = onBuildRequest();
		
		EHttpResponse response = onSubmit(request);
		
		return onParseResult(response);
	}

}
