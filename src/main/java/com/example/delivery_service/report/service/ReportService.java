package com.example.delivery_service.report.service;

import com.example.delivery_service.delivery_position.model.DeliveryPosition;
import com.example.delivery_service.delivery_position.service.PositionService;
import com.example.delivery_service.report.model.*;
import com.example.delivery_service.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportServiceInterface {
    private final ReportRepository reportRepository;
    private final PositionService positionService;

    @Override
    @Transactional
    public Report save(LocalDateTime startDate, LocalDateTime endDate) {
        List<DeliveryPosition> deliveryPositions = positionService.findAllByDelivery_ArrivalTimeBetween(startDate, endDate);
        Map<Long, Map<String, ReportDetail>> reportDetails = fillDetailsOfReport(deliveryPositions);

        List<ReportDetail> reports = new ArrayList<>();
        for (Map<String, ReportDetail> vendorDetails : reportDetails.values()) {
            reports.addAll(vendorDetails.values());
        }

        Report report = Report.builder()
            .endDate(endDate)
            .startDate(startDate)
            .build();
        report = reportRepository.save(report);

        for (ReportDetail detail : reports) {
            detail.setReport(report);
        }

        report.setReportDetails(reports);

        return reportRepository.save(report);
    }

    private Map<Long, Map<String, ReportDetail>> fillDetailsOfReport(List<DeliveryPosition> deliveryPositions) {
        Map<Long, Map<String, ReportDetail>> reportDetails = new HashMap<>();

        for (DeliveryPosition position : deliveryPositions) {
            Long vendorId = position.getDelivery().getVendor().getId();
            String productName = position.getProduct().getType().getName();

            reportDetails
                .computeIfAbsent(vendorId, k -> new HashMap<>())
                .computeIfAbsent(productName, k -> ReportDetail.builder()
                    .vendorId(vendorId)
                    .vendorName(position.getDelivery().getVendor().getName())
                    .product(productName)
                    .totalPrice(BigDecimal.ZERO)
                    .totalQuantity(0)
                    .build()
                );

            ReportDetail detail = reportDetails.get(vendorId).get(productName);
            detail.setTotalPrice(detail.getTotalPrice().add(position.getPrice().multiply(BigDecimal.valueOf(position.getQuantity()))));
            detail.setTotalQuantity(detail.getTotalQuantity() + position.getQuantity());
        }

        return reportDetails;
    }
}