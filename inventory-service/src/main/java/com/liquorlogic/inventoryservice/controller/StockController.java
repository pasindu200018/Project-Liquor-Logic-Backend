package com.liquorlogic.inventoryservice.controller;

import com.liquorlogic.inventoryservice.entity.Enum.Status;
import com.liquorlogic.inventoryservice.entity.Stock;
import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.service.StockService;
import com.liquorlogic.inventoryservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Nipuna Ruwan.
 *  
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    @Autowired
    private final StockService stockService;

    @Autowired
    private SupplierService supplierService;

    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(StockController.class);

    @PostMapping("/save")
    public ResponseEntity<Stock> saveStock(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start saveStock");
        try {
            String[] requiredFileds = { "supplierId",  "brandId",
                    "discription", "image", "QTY", "createBy", "updateBy"};
            validateMap(credentials, requiredFileds);

            Stock stock = new Stock();
            UUID stockId = credentials.get("itemId") != null ? UUID.fromString(credentials.get("itemId")) : null;

            if (stockId != null) {
                Optional<Stock> byID = (stockService.getStockById(stockId));
                if (byID.isPresent()) {
                    stock.setStockId(stockId);

                }
            }
            UUID supplierId =(UUID.fromString(credentials.get("supplierId")));
            Supplier supplier = supplierService.findBySupplierId(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"));
            stock.setSupplier(supplier);
            stock.setBrandId(UUID.fromString(credentials.get("brandId")));
            stock.setDiscription(credentials.get("description"));
            stock.setImage(credentials.get("image"));
            stock.setQTY(Integer.parseInt(credentials.get("QTY")));
            stock.setCreateBy(credentials.get("createBy"));
            stock.setUpdateBy(credentials.get("updateBy"));
            stock.setStatus(Status.valueOf(credentials.get("status")));



            Date currentDate = new Date();
            stock.setUpdateDate(currentDate);
            if (stockId == null) {
                stock.setCreateDate(currentDate);
            }
            return ResponseEntity.ok(stockService.createStock(stock));
        } catch (Exception e) {
            handleException(e);
            loggerLog4J.error("Error Occurred while saving Item");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            loggerLog4J.info("End saveStock");
        }

    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllItem() {
        loggerLog4J.info("Start getAllStock");
        try {
            loggerLog4J.info("End getAllStock");
            return ResponseEntity.ok(stockService.getAllStocks());
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PostMapping ("/stockId")
    public ResponseEntity <Optional<Stock>> findByStockId (@RequestParam UUID stockId){
        loggerLog4J.info("Start findByItemId ");
        try {
            loggerLog4J.info("End findByItemId ");
            Optional<Stock> stock = stockService.getStockById(stockId);
            if (stock.isPresent()) {
                return ResponseEntity.ok(Optional.of(stock.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @DeleteMapping
    public ResponseEntity<String> deleteStock(@RequestParam UUID stockId) {
        loggerLog4J.info("Start deleteItem");

        // Find the item by itemId
        Optional<Stock> optionalStock = stockService.getStockById(stockId);

        if (optionalStock.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }

        try {
            Stock stock = optionalStock.get();
            stockService.deleteStock(stock);
            loggerLog4J.info("End deleteStock");
            return ResponseEntity.ok("Stock Deleted Successfully");

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

//    @PostMapping ("/itemId")
//    public ResponseEntity <Stock> findByItemId (@RequestParam UUID itemId){
//        loggerLog4J.info("Start findByItemId");
//        try {
//            loggerLog4J.info("End findByItemId");
//            Stock stock = stockService.findByItemId(itemId);
//            if (stock != null) {
//                return ResponseEntity.ok(stock);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//
//        } catch (Exception e) {
//            handleException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }



    private void validateMap(Map<String, String> assetCategoryMap, String[] requiredFileds) {

        for (String field : requiredFileds) {
            if (assetCategoryMap.get(field) == null || assetCategoryMap.get(field).isEmpty()) {
                throw new IllegalArgumentException("Not found " + field);
            }
        }
    }


    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }
}
