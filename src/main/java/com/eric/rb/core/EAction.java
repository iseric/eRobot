package com.eric.rb.core;

import com.eric.rb.action.ActionClassEnum;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;

/**
 * @FileName: EAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public interface EAction extends ELifeCycle{
	
	public enum Type{
		CHECK_VERIFY(ActionClassEnum.CHECK_VERIFY),
		GET_VERIFY_IMAGE(ActionClassEnum.GET_VERIFY_IMAGE),
		LOGIN1(ActionClassEnum.LOGIN1),
		CHECK_SIG(ActionClassEnum.CHECK_SIG),
		LOGIN2(ActionClassEnum.LOGIN2),
		POLL(ActionClassEnum.POLL),
		SEND_MSG(ActionClassEnum.SEND_MSG),
		
		
		
		END(ActionClassEnum.END);
		private ActionClassEnum actionEnum;
		Type(ActionClassEnum e){
			this.actionEnum = e;
		}
		public ActionClassEnum getActionEnum(){
			return actionEnum;
		}
	}
	
	public EHttpRequest onBuildRequest();
	
	public EHttpResponse onSubmit(EHttpRequest request);
	
	public Object onParseResult(EHttpResponse response);
	
	public Object execute();
}
