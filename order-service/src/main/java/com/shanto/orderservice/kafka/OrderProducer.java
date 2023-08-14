package com.shanto.orderservice.kafka;

import com.shanto.basedomains.dto.OrderEventDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEventDto> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEventDto orderEventDto){

        LOGGER.info(String.format("Order event => %s", orderEventDto.toString()));

        //create a message
        Message<OrderEventDto> message = MessageBuilder
                .withPayload(orderEventDto)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
