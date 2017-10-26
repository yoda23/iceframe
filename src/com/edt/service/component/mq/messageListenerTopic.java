package com.edt.service.component.mq;

import com.edt.entity.User;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.annotation.Resource;
import javax.jms.*;

public class messageListenerTopic implements SessionAwareMessageListener {
	@Resource(name = "queueDestinationTopic")
	private Destination queueDestinationTopicDestination;

	@Override
	public void onMessage(Message message, Session session)
			throws JMSException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			Thread.sleep(5000);
			User user = (User) objectMessage.getObject();
			System.out.println("消息内容1111111111是：" + user.getId());
		} catch (JMSException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
