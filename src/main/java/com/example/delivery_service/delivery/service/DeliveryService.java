package com.example.delivery_service.delivery.service;

import com.example.delivery_service.core.exception.exceptions.DeliveryNotFoundException;
import com.example.delivery_service.delivery.dto.DeliveryDto;
import com.example.delivery_service.delivery.model.Delivery;
import com.example.delivery_service.delivery.repository.DeliveryRepository;
import com.example.delivery_service.delivery_position.dto.*;
import com.example.delivery_service.delivery_position.model.DeliveryPosition;
import com.example.delivery_service.delivery_position.service.PositionService;
import com.example.delivery_service.product.model.Product;
import com.example.delivery_service.product.service.ProductService;
import com.example.delivery_service.vendor.model.Vendor;
import com.example.delivery_service.vendor.service.VendorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeliveryService implements DeliveryServiceInterface {
    private final DeliveryRepository repository;
    private final VendorService vendorService;
    private final ProductService productService;
    private final PositionService positionService;

    public DeliveryService(
        DeliveryRepository repository,
        VendorService vendorService,
        ProductService productService,
        @Lazy PositionService positionService
    ) {
        this.repository = repository;
        this.vendorService = vendorService;
        this.productService = productService;
        this.positionService = positionService;
    }

    @Override
    @Transactional
    public DeliveryDto save(DeliveryDto dto) {
        Vendor vendor = vendorService.getExistingVendorById(dto.getVendorId());

        Delivery delivery = Delivery.builder()
            .arrivalTime(dto.getArrivalTime())
            .vendor(vendor)
            .build();

        delivery = repository.save(delivery);
        List<PositionDto> positions = new ArrayList<>();

        for (PositionDto dtoPosition : dto.getPositions()) {
            Product product = productService.getExistingProductById(dtoPosition.getProductId());

            DeliveryPosition deliveryPosition = DeliveryPosition.builder()
                .product(product)
                .quantity(dtoPosition.getQuantity())
                .price(dtoPosition.getPrice())
                .delivery(delivery)
                .build();

            dtoPosition = positionService.save(PositionMapper.toDtoFromPosition(deliveryPosition));
            positions.add(dtoPosition);
        }

        return DeliveryDto.builder()
            .positions(positions)
            .vendorId(delivery.getVendor().getId())
            .arrivalTime(delivery.getArrivalTime())
            .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Delivery> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        getExistingDeliveryById(id);
        repository.deleteById(id);
    }

    public Delivery getExistingDeliveryById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new DeliveryNotFoundException("Поставка с id " + id + " не найдена.")
        );
    }
}