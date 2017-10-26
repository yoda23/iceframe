package com.edt.service.component.mq;

import com.edt.entity.User;
import com.edt.service.MqSendService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class MqSendServiceImpl implements MqSendService {
	@Resource(name = "jmsTemplateTopic")
	private JmsTemplate jmsTemplateTopic;
	@Resource(name = "jmsTemplatePoint")
	private JmsTemplate jmsTemplatePoint;
	@Resource(name = "queueDestinationTopic")
	private Destination queueDestinationTopicDestination;
    @Resource(name = "queueDestinationPoint")
    private Destination queueDestinationPointDestination;
	@Override
	public void sendMessageToPoint(final User user) {
		jmsTemplatePoint.send(queueDestinationPointDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(user);
			}
		});
	}

	@Override
	public void sendMessageToTopic(final User user) {
		jmsTemplatePoint.send(queueDestinationTopicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(user);
			}
		});
	}
}
