package com.liquorlogic.posservice.serviceImpl;


import com.liquorlogic.posservice.entity.Item;
import com.liquorlogic.posservice.entity.Order;
import com.liquorlogic.posservice.enums.OrderStatus;
import com.liquorlogic.posservice.repository.ItemRepository;
import com.liquorlogic.posservice.repository.OrderRepository;
import com.liquorlogic.posservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public boolean deleteOrder(Order orderId) {
        orderRepository.delete(orderId);
        return true;
    }

    @Override
    public Optional<Order> findByOrderId(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order findByItemId(UUID itemId) {
        return orderRepository.findByItemId(itemId);
    }

    @Override
    public Order findByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order findByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order findByCreateDate(Date createDate) {
        return orderRepository.findByCreateDate(createDate);
    }

    @Override
    public Order findByUpdateDate(Date updateDate) {
        return orderRepository.findByUpdateDate(updateDate);
    }

    @Transactional
    public Order placeOrder(UUID itemId,UUID userId , int orderQty) throws Exception {
        // Retrieve the item from the repository
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (!optionalItem.isPresent()) {
            throw new Exception("Item not found");
        }

        Item item = optionalItem.get();

        // Check if the item has sufficient quantity
        if (item.getQty() < orderQty) {
            throw new Exception("Insufficient quantity in stock");
        }

        // Reduce the item quantity
        item.setQty(item.getQty() - orderQty);
        itemRepository.save(item);

        // Calculate total amount
        double totalAmount = orderQty * item.getUnitPrice();

        // Create and save the order
        Order order = new Order();
        order.setItemId(itemId);
        order.setUserId(userId);
        order.setQty(orderQty);
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.Pending); // Assuming Pending is a valid status

        return orderRepository.save(order);
    }
}




