package dev.batugokce.cdc.stockservice.kafka.consumer;

import dev.batugokce.cdc.stockservice.kafka.event.CdcEvent;
import dev.batugokce.cdc.stockservice.stock.service.DeleteStockService;
import dev.batugokce.cdc.stockservice.stock.service.UpdateStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventConsumer {

    private final UpdateStockService updateStockService;
    private final DeleteStockService deleteStockService;

    @KafkaListener(topics = "product.product_schema.product", id = "stock-service-consumer")
    public void processEvent(@Payload(required = false) CdcEvent event) {
        switch (event.getOperationType()) {
            case 'c', 'r', 'u' -> {
                var product = event.getPayload().getAfter();
                updateStockService.updateStock(product.getId(), product.getQuantityLeft());
            }
            case 'd' -> {
                var productIdToDelete = event.getPayload().getBefore().getId();
                deleteStockService.deleteByProductId(productIdToDelete);
            }
        }
    }

}
