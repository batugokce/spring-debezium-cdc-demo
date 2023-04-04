package dev.batugokce.cdc.stockservice.stock.service;

import dev.batugokce.cdc.stockservice.stock.entity.Stock;
import dev.batugokce.cdc.stockservice.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStockService {

    private final StockRepository stockRepository;

    @Transactional
    public void updateStock(Long productId, Integer newQuantity) {
        stockRepository.findByProductId(productId).ifPresentOrElse(stock -> {
            stock.setQuantity(newQuantity);
            stockRepository.save(stock);
        }, () -> createNewStockEntry(productId, newQuantity));
    }

    private void createNewStockEntry(Long productId, Integer quantity) {
        var stock = Stock.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
        stockRepository.save(stock);
    }

}
