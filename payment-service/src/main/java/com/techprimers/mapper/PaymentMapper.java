package com.techprimers.mapper;

import com.techprimers.model.Order;
import com.techprimers.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment convert(Order orderResponse) {
        return Payment.builder()
                .orderId(orderResponse.getOrderId())
                .customerId(orderResponse.getCustomerId())
                .sellerId(orderResponse.getSellerId())
                .paymentMethod(orderResponse.getPaymentMethod())
                .paymentStatus("SUCCESS")
                .paymentStatusNotes("Successfully paid by " + orderResponse.getPaymentMethod())
                .build();
    }
}
