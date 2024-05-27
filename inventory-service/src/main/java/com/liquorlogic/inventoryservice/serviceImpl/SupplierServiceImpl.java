package com.liquorlogic.inventoryservice.serviceImpl;

import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import com.liquorlogic.inventoryservice.repository.SupplierRepository;
import com.liquorlogic.inventoryservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private  final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return null;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    @Override
    public List<Supplier> findAllByStatus(SupplierStatus status) {
        return null;
    }

    @Override
    public Optional<Supplier> findSupplierById(UUID supplierId) {
        return Optional.empty();
    }

    @Override
    public List<Supplier> findByContact(String contact) {
        return null;
    }

    @Override
    public Optional<Supplier> findBySupplierId(UUID supplierId) {
        return Optional.empty();
    }
}
