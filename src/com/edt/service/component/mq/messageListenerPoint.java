package com.edt.service.component.mq;

import com.edt.entity.User;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component
public class messageListenerPoint
		implements SessionAwareMessageListener {
    @Resource(name = "queueDestinationPoint")
    private Destination queueDestinationPointDestination;

	@Override
	public void onMessage(Message message, Session session)
			throws JMSException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			Thread.sleep(500);
			User user = (User) objectMessage.getObject();
			System.out.println("消息内容是：" + user.getId());
		} catch (JMSException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
