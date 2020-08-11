package com.azure.demo.service.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.azure.demo.model.Message;

@Component
public class TopicReceiveController {

	private static final String TOPIC_NAME = "my.service.bus.204";

	private static final String SUBSCRIPTION_NAME = "my.service.bus.subscription.204";

	private final Logger logger = LoggerFactory.getLogger(TopicReceiveController.class);

	@JmsListener(destination = TOPIC_NAME, containerFactory = "topicJmsListenerContainerFactory", subscription = SUBSCRIPTION_NAME)
	public void receiveMessage(Message msg) {
		logger.info("Received message: {}", msg.getTxt());
	}
}
