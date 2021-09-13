package com.techprimers.service;

import com.techprimers.mapper.OrderMapper;
import com.techprimers.model.Order;
import com.techprimers.publisher.OrderPublisher;
import com.techprimers.repository.OrderRepository;
import com.techprimers.shared.CreateOrderRequest;
import com.techprimers.shared.GetOrderRequest;
import com.techprimers.shared.OrderResponse;
import com.techprimers.shared.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderPublisher orderPublisher;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

        Order order = orderMapper.convert(request);
        orderRepository.save(order);
        orderPublisher.publish(order);

        OrderResponse orderResponse = orderMapper.convert(order);

        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

        Order orderById = orderRepository.getOrderById(request.getOrderId());

        OrderResponse orderResponse = orderMapper.convert(orderById);

        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();

    }
}
