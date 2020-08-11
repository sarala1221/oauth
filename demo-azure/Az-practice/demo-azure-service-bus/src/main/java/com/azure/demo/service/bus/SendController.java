package com.azure.demo.service.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azure.demo.model.Message;

@RestController
public class SendController {

	private final static String DEST_QUEUE = "my.queue.204";
	private final static Logger log = LoggerFactory.getLogger(SendController.class);
	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping("/messages")
	public String postMessage(@RequestParam String message) {
		log.info("sending message :{}", message);

		jmsTemplate.convertAndSend(DEST_QUEUE, new Message(message));

		return message;

	}
}
