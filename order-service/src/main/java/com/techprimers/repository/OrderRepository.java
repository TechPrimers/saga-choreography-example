package com.techprimers.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.techprimers.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private DynamoDBMapper dynamoDBMapper;

    public OrderRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Order save(Order order) {
        dynamoDBMapper.save(order);
        return order;
    }

    public Order getOrderById(String orderId) {
        Order order = null;
        try {
            order = dynamoDBMapper.load(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception, Returning empty order");
            order = new Order();
        }
        return order;
    }
}
