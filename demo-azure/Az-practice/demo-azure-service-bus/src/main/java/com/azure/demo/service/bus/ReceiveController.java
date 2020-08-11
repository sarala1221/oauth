package com.azure.demo.service.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.azure.demo.model.Message;

@Component
public class ReceiveController {

	private final static String QUEUE_NAME = "my.queue.204";

	private static final Logger log = LoggerFactory.getLogger(ReceiveController.class);

	@JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(Message message) {

		log.info("Message Received ::{}", message.getTxt());

	}

}
