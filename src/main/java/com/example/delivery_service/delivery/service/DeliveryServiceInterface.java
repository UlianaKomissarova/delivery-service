package com.example.delivery_service.delivery.service;

import com.example.delivery_service.delivery.dto.DeliveryDto;
import com.example.delivery_service.delivery.model.Delivery;

import java.util.List;

public interface DeliveryServiceInterface {
    DeliveryDto save(DeliveryDto dto);

    List<Delivery> findAll();

    void delete(Long id);
}