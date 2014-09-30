package com.eric.rb.action;

import com.eric.rb.core.EAction;
import com.eric.rb.core.EContext;

/**
 * @FileName: ActionFactory.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class ActionFactory {
	public static EAction getAction(EAction.Type actionType,EContext context){
		EAction action = null;
		try {
			action = (EAction)Class.forName(actionType.getActionEnum().getClassName()).newInstance();
			action.init(context);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return action;
	}
}
