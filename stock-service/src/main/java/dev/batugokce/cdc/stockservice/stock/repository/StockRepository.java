package dev.batugokce.cdc.stockservice.stock.repository;

import dev.batugokce.cdc.stockservice.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(long productId);

    void deleteByProductId(long productId);

}
