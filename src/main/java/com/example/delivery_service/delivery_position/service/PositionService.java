package com.example.delivery_service.delivery_position.service;

import com.example.delivery_service.delivery.model.Delivery;
import com.example.delivery_service.delivery.service.DeliveryService;
import com.example.delivery_service.delivery_position.dto.PositionDto;
import com.example.delivery_service.delivery_position.model.DeliveryPosition;
import com.example.delivery_service.delivery_position.repository.PositionRepository;
import com.example.delivery_service.product.model.Product;
import com.example.delivery_service.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.delivery_service.delivery_position.dto.PositionMapper.*;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionServiceInterface {
    private final PositionRepository repository;
    private final DeliveryService deliveryService;
    private final ProductService productService;

    @Override
    @Transactional
    public PositionDto save(PositionDto dto) {
        DeliveryPosition position = toPositionFromDto(dto);

        Delivery delivery = deliveryService.getExistingDeliveryById(dto.getDeliveryId());
        Product product = productService.getExistingProductById(dto.getProductId());

        position.setDelivery(delivery);
        position.setProduct(product);

        return toDtoFromPosition(repository.save(position));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryPosition> findAllByDelivery_ArrivalTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findAllByDelivery_ArrivalTimeBetween(startDate, endDate);
    }
}