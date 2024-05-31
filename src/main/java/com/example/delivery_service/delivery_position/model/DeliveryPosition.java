package com.example.delivery_service.delivery_position.model;

import com.example.delivery_service.delivery.model.Delivery;
import com.example.delivery_service.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery_positions")
public class DeliveryPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private BigDecimal price;
    @Column
    private int quantity;
}