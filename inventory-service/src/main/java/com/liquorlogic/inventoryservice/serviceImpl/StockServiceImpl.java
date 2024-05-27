package com.liquorlogic.inventoryservice.serviceImpl;

import com.liquorlogic.inventoryservice.entity.Stock;
import com.liquorlogic.inventoryservice.repository.StockRepository;
import com.liquorlogic.inventoryservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    @Autowired
    private final StockRepository stockRepository;
    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> getStockById(UUID stockId) {
        return stockRepository.findById(stockId);
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public boolean deleteStock(Stock stock) {
        stockRepository.delete(stock);
        return true;
    }


}
