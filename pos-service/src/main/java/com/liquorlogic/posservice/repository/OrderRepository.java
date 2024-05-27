package com.liquorlogic.posservice.repository;


import com.liquorlogic.posservice.entity.Order;
import com.liquorlogic.posservice.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findByItemId(UUID itemId);

    Order findByUserId(UUID userId);

    Order findByStatus(OrderStatus status);

    Order findByCreateDate(Date createDate);

    Order findByUpdateDate(Date updateDate);
}
