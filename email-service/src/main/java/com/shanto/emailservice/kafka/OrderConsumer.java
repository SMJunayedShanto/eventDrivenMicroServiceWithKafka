package com.shanto.emailservice.kafka;

import com.shanto.basedomains.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
                   groupId = "${spring.kafka.consumer.group-id}"
                  )
    public void consume(OrderEventDto orderEventDto){

        LOGGER.info(String.format("Order event received in email service => %s", orderEventDto));

        //TODO: send email to customer from here

    }
}
