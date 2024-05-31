package com.example.delivery_service.delivery_position.repository;

import com.example.delivery_service.delivery_position.model.DeliveryPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<DeliveryPosition, Long> {
    List<DeliveryPosition> findAllByDelivery_ArrivalTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}