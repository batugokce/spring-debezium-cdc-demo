package dev.batugokce.cdc.stockservice.stock.service;

import dev.batugokce.cdc.stockservice.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStockService {

    private final StockRepository stockRepository;

    @Transactional
    public void deleteByProductId(long productId) {
        stockRepository.deleteByProductId(productId);
    }

}
