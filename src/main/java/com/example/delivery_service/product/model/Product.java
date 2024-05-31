package com.example.delivery_service.product.model;

import com.example.delivery_service.product_type.model.ProductType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType type;
}