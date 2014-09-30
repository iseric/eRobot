package com.eric.rb.module;

import com.eric.rb.action.ActionFactory;
import com.eric.rb.bean.EMessage;
import com.eric.rb.consts.KeyConsts;
import com.eric.rb.core.EAction;
import com.eric.rb.core.EMsg;
import com.eric.rb.msg.handler.MsgHandlerMap;
import com.eric.rb.msg.queue.AshmanQueue;
import com.eric.rb.msg.queue.MsgSendQueue;

/**
 * @FileName: MessageModule.java
 * @Description:
 * @author Eric
 * @date 2014年9月28日
 */
public class MessageModule extends AbstractModule{
	
	/**
	 * 轮询方法, 接收消息需要发送http请求阻塞式等待服务端返回结果.
	 * 如没有消息, 1分钟后返回102应答码
	 * 如有消息, 即刻同步返回消息(可能多条), 应答码为0,  获取后重新发送poll请求
	 * 
	 * 此处设置为接收消息后加入消息处理队列, 加入队列后重新poll请求, 由消费线程发送消息
	 */
	public void pollMsg(){
		while(context.getSession().isRun()){
			EAction action = ActionFactory.getAction(EAction.Type.POLL, context);
			Object obj = action.execute();
			if(obj != null){
				EMessage message = processMsg((EMessage)obj);
				if(message != null){
					sendMsg(message);
				}
			}
		}
	}
	public EMessage processMsg(EMessage message){
		EMsg handler = MsgHandlerMap.getMsgHandler(message.getPollType());
		return handler.autoReply(context, message);
	}
	
	
	public void sendMsg(EMessage message){
		context.getParams().put(KeyConsts.PARAM, message);
		EAction actions = ActionFactory.getAction(EAction.Type.SEND_MSG, context);
		actions.execute();
	}
	

	/**
	 * 从待处理消息队列中提取有用数据进行处理, 并加入发送队列
	 * 
	 */
	public void intercepMsgThread(){
		new Thread(new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						System.out.println("消息过滤器等待中...");
						EMessage message = AshmanQueue.getMessage();
						EMsg handler = MsgHandlerMap.getMsgHandler(message.getPollType());
						message = handler.autoReply(context, message);
						
						if(message != null){
							context.getParams().put(KeyConsts.PARAM, message);
							EAction action = ActionFactory.getAction(EAction.Type.SEND_MSG, context);
							action.execute();
						}
					
					
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e){
						e.printStackTrace();
					}
					System.out.println("消息过滤处理完成");
				}
			}
		}).start();
	}
	
	/**
	 * 将发送队列中的消息进行一次发送
	 */
	public void sendMsgThread(){
		new Thread(new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						System.out.println("等待发送消息...");
						EMessage message = MsgSendQueue.getMessage();
						context.getParams().put(KeyConsts.PARAM, message);
						EAction action = ActionFactory.getAction(EAction.Type.SEND_MSG, context);
						action.execute();
					
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e){
						e.printStackTrace();
					}
					System.out.println("消息发送完毕");
				}
			}
		}).start();
	}
}
