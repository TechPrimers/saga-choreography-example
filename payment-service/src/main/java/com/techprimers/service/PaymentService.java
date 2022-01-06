package com.techprimers.service;

import com.techprimers.mapper.PaymentMapper;
import com.techprimers.model.Order;
import com.techprimers.model.OrderState;
import com.techprimers.model.OrderStatus;
import com.techprimers.model.Payment;
import com.techprimers.publisher.PaymentPublisher;
import com.techprimers.repository.PaymentRespository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentMapper paymentMapper;
    private PaymentRespository paymentRepository;
    private PaymentPublisher paymentPublisher;

    public PaymentService(PaymentMapper paymentMapper, PaymentRespository paymentRepository, PaymentPublisher paymentPublisher) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentPublisher = paymentPublisher;
    }

    public void send(Order orderResponse) {
        Payment payment = paymentMapper.convert(orderResponse);
        paymentRepository.save(payment);
        paymentPublisher.publish(payment);
        OrderStatus orderStatus = OrderStatus.builder()
                .orderState(OrderState.ORDER_PAID)
                .orderId(orderResponse.getOrderId())
                .message("Successfully paid by " + payment.getPaymentMethod())
                .build();
        paymentPublisher.publish(orderStatus);

    }
}
