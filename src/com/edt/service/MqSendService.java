package com.edt.service;

import com.edt.entity.User;

public interface MqSendService {
	/**
	 * 发送点对点队列
	 * 
	 * @param user
	 *            user
	 * @author 刘钢 2017-06-20 13:18
	 */

	void sendMessageToPoint(User user);

	/**
	 * 发送发布/订阅队列
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017-06-20 13:18
	 */
	void sendMessageToTopic(User user);
}
