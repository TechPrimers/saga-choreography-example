package com.techprimers.publisher;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.techprimers.model.OrderStatus;
import com.techprimers.model.Payment;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentPublisher {

    private QueueMessagingTemplate queueMessagingTemplate;

    public PaymentPublisher(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void publish(Payment payment) {
        System.out.println("Publishing orderId: " + payment.getOrderId());
        queueMessagingTemplate.convertAndSend("restaurant-updates", payment);
    }

    public void publish(OrderStatus orderStatus) {
        System.out.println("Publishing orderStatus to orderId: " + orderStatus.getOrderId());
        queueMessagingTemplate.convertAndSend("order-updates", orderStatus);
    }
}
