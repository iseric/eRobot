package com.eric.rb.msg.queue;

import java.util.concurrent.ArrayBlockingQueue;

import com.eric.rb.bean.EMessage;
import com.eric.rb.consts.ContextConsts;

/**
 * @FileName: MsgSendQueue.java
 * @Description: QQ消息待发送队列
 * @author Eric
 * @date 2014年9月29日
 */
public class MsgSendQueue {
	private static ArrayBlockingQueue<EMessage> sendMsgQueue = new ArrayBlockingQueue<EMessage>(
			ContextConsts.MSG_QUEUE_ZISE);

	/**
	 * 如果队列长度最大值 ContextConsts.MSG_QUEUE_ZISE, 则阻塞.
	 * 
	 * @param message
	 */
	public static void putMessage(EMessage message) {
		try {
			sendMsgQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 阻塞式等待生产者生产数据
	 * 
	 * @return
	 */
	public static EMessage getMessage() {
		try {
			return sendMsgQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
