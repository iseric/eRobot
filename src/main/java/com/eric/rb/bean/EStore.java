package com.eric.rb.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: EStore.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class EStore {

	private final Map<Long, EBuddy> buddys = new HashMap<Long, EBuddy>();
	private final Map<Long, EGroup> groups = new HashMap<Long, EGroup>();
	private final Map<Long, EDiscuz> discuzs = new HashMap<Long, EDiscuz>();
	
	// ///////////////////////////buddy/////////////////////////////
	public EBuddy getBuddyByUin(long uin) {
		return this.buddys.get(uin);
	}

	public void addBuddy(EBuddy buddy) {
		this.buddys.put(buddy.getUin(), buddy);
	}

	public void removeBuddy(EBuddy buddy) {
		this.buddys.remove(buddy.getUin());
	}

	// ///////////////////////////group/////////////////////////////
	public EGroup getGroupByGroupCode(long groupCode) {
		return groups.get(groupCode);
	}

	public void addGroup(EGroup group) {
		this.groups.put(group.getGroupCode(), group);
	}

	public void removeGroup(EGroup group) {
		this.groups.remove(group.getGroupCode());
	}

	// ///////////////////////////discuz/////////////////////////////
	public void addDiscuz(EDiscuz discuz) {
		this.discuzs.put(discuz.getDid(), discuz);
	}

	public void removeDiscuz(EDiscuz discuz) {
		this.discuzs.remove(discuz.getDid());
	}
}
