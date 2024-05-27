package com.liquorlogic.inventoryservice.repository;

import com.liquorlogic.inventoryservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;




public interface StockRepository extends JpaRepository<Stock, UUID> {


}
