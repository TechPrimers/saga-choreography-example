package com.techprimers.model;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderStatus {
    private String orderId;
    private OrderState orderState;
    private String message;
}
