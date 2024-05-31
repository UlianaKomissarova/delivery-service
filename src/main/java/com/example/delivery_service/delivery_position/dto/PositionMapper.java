package com.example.delivery_service.delivery_position.dto;

import com.example.delivery_service.delivery_position.model.DeliveryPosition;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {
    public static PositionDto toDtoFromPosition(DeliveryPosition position) {
        return PositionDto.builder()
            .id(position.getId())
            .productId(position.getProduct().getId())
            .deliveryId(position.getDelivery().getId())
            .price(position.getPrice())
            .quantity(position.getQuantity())
            .build();
    }

    public static DeliveryPosition toPositionFromDto(PositionDto dto) {
        return DeliveryPosition.builder()
            .id(dto.getId())
            .price(dto.getPrice())
            .quantity(dto.getQuantity())
            .build();
    }
}