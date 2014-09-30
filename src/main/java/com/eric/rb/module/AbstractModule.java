package com.eric.rb.module;

import com.eric.rb.core.EAction;
import com.eric.rb.core.EContext;
import com.eric.rb.core.ELifeCycle;
import com.eric.rb.core.EModule;

/**
 * @FileName: AbstractModule.java
 * @Description:
 * @author Eric
 * @date 2014年9月26日
 */
public class AbstractModule implements EModule,ELifeCycle{
	
	protected EContext context;
	
	public void init(EContext context){
		this.context = context;
	}
	
	public EAction getAction(EAction.Type actionType){
		
		return null;
	}
}
