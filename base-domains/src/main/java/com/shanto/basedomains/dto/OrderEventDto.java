package com.shanto.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventDto {

    private String message;
    private String status;
    private OrderDto orderDto;
}
