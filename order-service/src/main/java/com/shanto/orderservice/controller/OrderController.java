package com.shanto.orderservice.controller;

import com.shanto.basedomains.dto.OrderDto;
import com.shanto.basedomains.dto.OrderEventDto;
import com.shanto.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

//    @Autowired
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderDto orderDto){

        orderDto.setOrderId(UUID.randomUUID().toString());

        OrderEventDto orderEventDto = new OrderEventDto();
        orderEventDto.setStatus("PENDING");
        orderEventDto.setMessage("Order is Pending!");
        orderEventDto.setOrderDto(orderDto);

        orderProducer.sendMessage(orderEventDto);

        return "Order Placed Successfully.....!";
    }
}
