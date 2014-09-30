package com.eric.rb.core;

import java.util.Map;

import com.eric.rb.bean.ESession;
import com.eric.rb.bean.EStore;
import com.eric.rb.bean.EUser;

/**
 * @FileName: EContent.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public interface EContext {
	
	public EStore getStore();

	public EUser getUser();
	
	public ESession getSession();
	
	public Map<String,Object> getParams();
	
	public String getRunTime();
}
