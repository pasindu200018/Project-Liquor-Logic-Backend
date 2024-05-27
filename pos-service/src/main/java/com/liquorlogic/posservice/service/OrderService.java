package com.liquorlogic.posservice.service;



import com.liquorlogic.posservice.entity.Order;
import com.liquorlogic.posservice.enums.OrderStatus;

import java.util.*;


public interface OrderService {
    Order saveOrder (Order order);
    List<Order> getAllOrder();
    boolean deleteOrder (Order orderId);
    Optional<Order> findByOrderId (UUID orderId);
    Order findByItemId (UUID itemId);
    Order findByUserId (UUID userId);
    Order findByStatus (OrderStatus status);
    Order findByCreateDate (Date createDate);
    Order findByUpdateDate (Date updateDate);


    Order placeOrder(UUID itemId ,UUID userId, int qty) throws Exception;
}
