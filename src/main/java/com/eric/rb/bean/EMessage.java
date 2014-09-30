package com.eric.rb.bean;

import com.alibaba.fastjson.JSONArray;
import com.eric.rb.core.EMsg;

/**
 * @FileName: EMessage.java
 * @Description: 消息类
 * @author Eric
 * @date 2014年9月27日
 */
public class EMessage {
//	private String pollType;
	private EMsg.Type pollType;
	private Long fromUin;
	private String toUin;
	private String msgId;
	private String msgId2;
	private String msgType;
	private String replyIp;
	private String groupCode;
	private String sendUin;
	private String did;
	private String seq;
	private String time;
	private String infoSeq;
	private String content;
	private String msg;
	private JSONArray font;
	
	public Long getFromUin() {
		return fromUin;
	}
	public void setFromUin(Long fromUin) {
		this.fromUin = fromUin;
	}
	public String getToUin() {
		return toUin;
	}
	public void setToUin(String toUin) {
		this.toUin = toUin;
	}
	public String getMsgId2() {
		return msgId2;
	}
	public void setMsgId2(String msgId2) {
		this.msgId2 = msgId2;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getSendUin() {
		return sendUin;
	}
	public void setSendUin(String sendUin) {
		this.sendUin = sendUin;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInfoSeq() {
		return infoSeq;
	}
	public void setInfoSeq(String infoSeq) {
		this.infoSeq = infoSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getReplyIp() {
		return replyIp;
	}
	public void setReplyIp(String replyIp) {
		this.replyIp = replyIp;
	}
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public EMsg.Type getPollType() {
		return pollType;
	}
	public void setPollType(EMsg.Type pollType) {
		this.pollType = pollType;
	}
	public JSONArray getFont() {
		return font;
	}
	public void setFont(JSONArray font) {
		this.font = font;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
//		System.out.println("pollType-->\t" + pollType);
//		System.out.println("fromUin-->\t" + fromUin);
//		System.out.println("content-->\t" + content);
//		System.out.println("=========================");
		return super.toString();
	}
}
