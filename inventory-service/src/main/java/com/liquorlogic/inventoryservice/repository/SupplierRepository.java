package com.liquorlogic.inventoryservice.repository;

import com.liquorlogic.inventoryservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
