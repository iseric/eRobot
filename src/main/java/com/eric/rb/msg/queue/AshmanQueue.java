package com.eric.rb.msg.queue;

import java.util.concurrent.LinkedBlockingQueue;

import com.eric.rb.bean.EMessage;
import com.eric.rb.consts.ContextConsts;

/**
 * @FileName: AshmanQueue.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class AshmanQueue {
	private static LinkedBlockingQueue<EMessage> pendingMsg = new LinkedBlockingQueue<EMessage>(ContextConsts.MSG_QUEUE_ZISE);
	
	/**
	 * 如果队列长度最大值 ContextConsts.MSG_QUEUE_ZISE, 则阻塞. 
	 * 
	 * @param message
	 */
	public static void putMessage(EMessage message){
		try {
			pendingMsg.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 阻塞式等待生产者生产数据
	 * 
	 * @return
	 */
	public static EMessage getMessage(){
		try {
			return pendingMsg.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
