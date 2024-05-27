package com.liquorlogic.posservice.serviceImpl;


import com.liquorlogic.posservice.entity.Item;
import com.liquorlogic.posservice.repository.ItemRepository;
import com.liquorlogic.posservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean deleteItem(Item itemId) {
        itemRepository.delete(itemId);
        return true;

    }

    @Override
    public Optional<Item> findByItemId(UUID itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public Item findBySupplierId(UUID supplierId) {
        return itemRepository.findBySupplierId(supplierId);
    }


    @Override
    public Item findByBrand(String brand) {
        return itemRepository.findByBrand(brand);
    }

    @Override
    public Item findByUnitPrice(Double unitPrice) {
        return itemRepository.findByUnitPrice(unitPrice);
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Item findByManufactureDate(Date manufactureDate) {
        return itemRepository.findByManufactureDate(manufactureDate);
    }

    @Override
    public Item findByExpireDate(Date expireDate) {
        return itemRepository.findByExpireDate(expireDate);
    }

    @Override
    public Item findByUserId(UUID userId) {
        return itemRepository.findByUserId(userId);
    }


}
