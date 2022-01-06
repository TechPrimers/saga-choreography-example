package com.techprimers.consumer;

import com.techprimers.model.Order;
import com.techprimers.service.PaymentService;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private PaymentService paymentService;

    public PaymentConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @SqsListener("payment-updates")
    public void consume(Order orderResponse) {
        System.out.println("Received order details for orderId..." + orderResponse.getOrderId());
        paymentService.send(orderResponse);
    }
}
