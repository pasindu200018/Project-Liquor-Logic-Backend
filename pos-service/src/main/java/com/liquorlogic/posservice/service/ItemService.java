package com.liquorlogic.posservice.service;



import com.liquorlogic.posservice.entity.Item;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ItemService {
    List<Item> getAllItem();
    Item saveItem (Item item);
    boolean deleteItem (Item itemId);
    Optional<Item> findByItemId (UUID itemId);

    Item findBySupplierId(UUID supplierId);

    Item findByBrand(String brand);

    Item findByUnitPrice(Double unitPrice);

    Item findByName(String name);

    Item findByManufactureDate(Date manufactureDate);

    Item findByExpireDate(Date expireDate);

    Item findByUserId (UUID userId);
}
