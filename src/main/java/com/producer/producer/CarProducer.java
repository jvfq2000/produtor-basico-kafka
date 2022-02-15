package com.producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.producer.controller.CarDTO;

@Service
public class CarProducer {
	private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);
	private final KafkaTemplate<String, CarDTO> kafkaTemplate;
	private final String topic;
	
	public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDTO> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void send(CarDTO carDTO) {
		kafkaTemplate.send(topic, carDTO).addCallback(
			success -> logger.info("Message success: " + success.getProducerRecord().value()),
			failure -> logger.info("Message failure: " + failure.getMessage())
		);
	}
}
