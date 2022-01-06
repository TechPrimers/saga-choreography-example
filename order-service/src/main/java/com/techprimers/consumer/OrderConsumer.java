package com.techprimers.consumer;

import com.techprimers.model.Order;
import com.techprimers.model.OrderStatus;
import com.techprimers.repository.OrderRepository;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderConsumer {

    private OrderRepository orderRepository;

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @SqsListener("order-updates")
    public void consume(OrderStatus orderStatus) {
        System.out.println("Received order status for orderId..."+ orderStatus.getOrderId());
        Order order = orderRepository.getOrderById(orderStatus.getOrderId());
        order.setOrderStatus(orderStatus.getOrderState().name());
        order.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        orderRepository.save(order);
    }
}
