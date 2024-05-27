package com.liquorlogic.inventoryservice.controller;

import com.liquorlogic.inventoryservice.dto.SupplierDTO;
import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import com.liquorlogic.inventoryservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {
    @Autowired
    private final SupplierService supplierService;

    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(SupplierController.class);

    //    REGISTER/UPDATE
    @PostMapping("/save")
    public ResponseEntity saveSupplier(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start register");
        try {
            String[] requiredFields = { "item_id", "supplier_name", "email", "contact", "status", "qty_revieved_items",
                    "buying_price", "payment", "payment_method",
                     "qty_reterned_items", "total_qty"};

            validateMap(credentials, requiredFields);

            Supplier supplier = new Supplier();
            UUID supplierId = credentials.get("id") != null ? UUID.fromString(credentials.get("id")) : null;

            if (supplierId != null) {
                Optional<Supplier> byID = supplierService.findBySupplierId(supplierId);
                if (byID.isPresent()) {
                    supplier.setId(supplierId);
                }
            }

            supplier.setItem_id(String.valueOf(UUID.fromString(credentials.get("item_id"))));
            supplier.setEmail(credentials.get("email"));
            supplier.setSupplier_name(credentials.get("supplier_name"));
            supplier.setStatus(SupplierStatus.valueOf(credentials.get("status")));
            supplier.setPayment(Double.parseDouble(credentials.get("payment")));
            supplier.setPayment_method(credentials.get("payment_method"));
            supplier.setTotal_qty(Integer.parseInt(credentials.get("total_qty")));
            supplier.setQty_revieved_items(Integer.parseInt(credentials.get("qty_revieved_items")));
            supplier.setQty_reterned_items(Integer.parseInt(credentials.get("qty_reterned_items")));
            supplier.setContact(credentials.get("contact"));
            supplier.setPayment_method(credentials.get("payment_method"));
            supplier.setBuying_price(Double.parseDouble(credentials.get("buying_price")));

            Date currentDate = new Date();
            supplier.setUpdate(currentDate);
            if (supplierId == null) {
                supplier.setCreate(currentDate);
            }
            return ResponseEntity.ok(supplierService.saveSupplier(supplier));
        } catch (Exception e) {
            handleException(e);
            loggerLog4J.info("End registration");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            loggerLog4J.info("End saveItem");
        }
    }

    private void validateMap(Map<String, String> credentials, String[] requiredFields) {
    }


    @GetMapping("/getAllSuppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        loggerLog4J.info("Start getAllSuppliers");
        try {
            List<Supplier> allSuppliers = supplierService.getAllSuppliers();
            List<SupplierDTO> responseSuppliers = convertToResponseSupplierList(allSuppliers);

            loggerLog4J.info("End getAllSuppliers");
            return ResponseEntity.ok(responseSuppliers);
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findSupplier")
    public ResponseEntity<SupplierDTO> getUser(@RequestParam(required = false) SupplierStatus status,
                                               @RequestParam(required = false) UUID supplierId,
                                               @RequestParam(required = false) String contact) {

        try {
            List<Supplier> supplierList;
            if (status != null) {
                supplierList = supplierService.findAllByStatus(status);
            } else if (supplierId != null) {
                Optional<Supplier> userById = supplierService.findSupplierById(supplierId);
                supplierList = userById.map(Collections::singletonList).orElseGet(Collections::emptyList);
            } else if (contact != null) {
                supplierList = supplierService.findByContact(contact);
            } else {
                return ResponseEntity.badRequest().build();
            }

            if (!supplierList.isEmpty()) {
                SupplierDTO responseUser = convertToResponseSupplierData(supplierList.get(0));
                loggerLog4J.info("End getUser");
                return ResponseEntity.ok(responseUser);
            } else {
                loggerLog4J.info("End getUser");
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<SupplierDTO> convertToResponseSupplierList(List<Supplier> allSuppliers) {

        if (allSuppliers != null && !allSuppliers.isEmpty()) {
            return allSuppliers.stream()
                    .map(this::convertToResponseSupplierData)
                    .toList();
        } else {
            return Collections.emptyList();
        }

    }

    private SupplierDTO convertToResponseSupplierData(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getItem_id(),
                supplier.getSupplier_name(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getQty_revieved_items(),
                supplier.getQty_reterned_items(),
                supplier.getTotal_qty(),
                supplier.getBuying_price(),
                supplier.getPayment(),
                supplier.getPayment_method(),
                supplier.getStatus(),
                supplier.getCreate(),
                supplier.getUpdate()
        );
    }

    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }
}
