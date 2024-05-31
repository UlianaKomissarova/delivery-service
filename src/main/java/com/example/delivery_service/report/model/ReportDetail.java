package com.example.delivery_service.report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_details")
@Schema(description = "Сущность позиции отчета")
public class ReportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id", nullable = false)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
    @Column(name = "vendor_id")
    private Long vendorId;
    @Column(name = "vendor_name")
    private String vendorName;
    @Column
    private String product;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "total_quantity")
    private int totalQuantity;
}