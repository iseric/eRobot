package com.eric.rb.EQQClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.eric.rb.bean.ESession;
import com.eric.rb.bean.EStore;
import com.eric.rb.bean.EUser;
import com.eric.rb.core.EContext;
import com.eric.rb.core.EModule;
import com.eric.rb.module.MessageModule;
import com.eric.rb.module.UserModule;
import com.eric.rb.utils.DateUtil;

/**
 * @FileName: EQQClient.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class EQQClient implements EContext {
	private EStore store;
	private EUser user;
	private ESession session;
	private Long startTime;
	
	private Map<String, Object> params = new HashMap<String, Object>();

	private Map<EModule.Type, EModule> modules;

	public EQQClient(String qqNum, String password) {
		startTime = System.currentTimeMillis();
		this.modules = new HashMap<EModule.Type, EModule>();

		this.modules.put(EModule.Type.USER, new UserModule());
		this.modules.put(EModule.Type.MESSAGE, new MessageModule());

		this.store = new EStore();
		this.session = new ESession();

		this.user = new EUser();
		this.user.setQqNum(qqNum);
		this.user.setPassword(password);
		
		this.init();
	}

	public void init() {
		session.setClientId(new Random().nextInt(10000000)+10000000);
		for (EModule.Type moduleType : modules.keySet()) {
			EModule action = modules.get(moduleType);
			action.init(this);
		}
	}
	
	public Map<String, Object> getParams() {
		return params;
	}
	public void putParame(String key,Object value){
		params.put(key, value);
	}
	
	public EModule getModule(EModule.Type type){
		return modules.get(type);
	}

	public EStore getStore() {
		return store;
	}
	
	public EUser getUser() {
		return user;
	}
	public ESession getSession() {
		return session;
	}
	public void setSession(ESession session) {
		this.session = session;
	}

	public String getRunTime() {
		return DateUtil.getRunTime(startTime);
	}
}
