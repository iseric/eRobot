package com.eric.rb.action;

import com.alibaba.fastjson.JSONObject;
import com.eric.rb.bean.EMessage;
import com.eric.rb.consts.URLConsts;
import com.eric.rb.core.EMsg;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.handler.HttpHandler;
import com.eric.rb.log.LOG;
import com.eric.rb.msg.handler.MsgHandlerMap;

/**
 * @FileName: PollAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class PollAction extends AbstractAction{

	public EHttpRequest onBuildRequest() {
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append(URLConsts.POLL_URL);
		requestUrl.append("?");
		requestUrl.append("clientid=");
		requestUrl.append(context.getSession().getClientId());
		requestUrl.append("&");
		requestUrl.append("psessionid=");
		requestUrl.append(context.getSession().getPsessionid());
		
		EHttpRequest request = new EHttpRequest();
		request.setUrl(requestUrl.toString());
		return request;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		return HttpHandler.get(request);
	}

	public Object onParseResult(EHttpResponse response) {
		try {
			String re =  response.getStringResult();
			LOG.println("result context: "+ re);
			JSONObject jsonObj = JSONObject.parseObject(response.getStringResult());
			if(jsonObj.getInteger("retcode") != 0){
				return null;
			}
			
			EMessage message = new EMessage();
			JSONObject resultObj = jsonObj.getJSONArray("result").getJSONObject(0);
			
			// set message type
			String pollType = resultObj.getString("poll_type");
			EMsg.Type type = MsgHandlerMap.getMsgType(pollType);
			if(type == null)
				return null;
			
			message.setPollType(type);
			
			JSONObject valueObj = resultObj.getJSONObject("value");
			
			String msgId = null;
			if((msgId = valueObj.getString("msg_id")) != null)
				message.setMsgId(msgId);
			
			Long fromUin = null;
			if((fromUin = valueObj.getLong("from_uin")) != null)
				message.setFromUin(fromUin);
			
			String toUin = null;
			if((toUin = valueObj.getString("to_uin")) != null)
				message.setToUin(toUin);
			
			String msgId2 = null;
			if((msgId2 = valueObj.getString("msg_id2")) != null)
				message.setMsgId2(msgId2);
			
			String did = null;
			if((did = valueObj.getString("did")) != null)
				message.setDid(did);
			
			String sendUin = null;
			if((sendUin = valueObj.getString("send_uin")) != null)
				message.setSendUin(sendUin);
			
			String msgType = null;
			if((msgType = valueObj.getString("msg_type")) != null)
				message.setMsgType(msgType);

			String replyIp = null;
			if((replyIp = valueObj.getString("reply_ip")) != null)
				message.setReplyIp(replyIp);

			String groupCode = null;
			if((groupCode = valueObj.getString("group_code")) != null)
				message.setGroupCode(groupCode);
			
			String time = null;
			if((time = valueObj.getString("time")) != null)
				message.setTime(time);
			
			String content = null;
			if((content =valueObj.getJSONArray("content").get(1).toString()) != null){
				message.setContent(content);
			}
			LOG.println("receive content: " + content);
			return message;
		} catch(ClassCastException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
