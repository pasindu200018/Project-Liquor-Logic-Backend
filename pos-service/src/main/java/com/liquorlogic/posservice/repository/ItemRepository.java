package com.liquorlogic.posservice.repository;


import com.liquorlogic.posservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;


public interface ItemRepository extends JpaRepository<Item, UUID> {
    Item findByUnitPrice(Double unitPrice);

    Item findByName(String name);

    Item findBySupplierId(UUID supplierId);

    Item findByManufactureDate(Date manufactureDate);

    Item findByExpireDate(Date expireDate);
    
    Item findByBrand(String brand);

    Item findByUserId(UUID userId);
}
