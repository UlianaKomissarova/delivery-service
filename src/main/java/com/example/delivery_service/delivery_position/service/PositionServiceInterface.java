package com.example.delivery_service.delivery_position.service;

import com.example.delivery_service.delivery_position.dto.PositionDto;
import com.example.delivery_service.delivery_position.model.DeliveryPosition;

import java.time.LocalDateTime;
import java.util.List;

public interface PositionServiceInterface {
    PositionDto save(PositionDto dto);

    List<DeliveryPosition> findAllByDelivery_ArrivalTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}