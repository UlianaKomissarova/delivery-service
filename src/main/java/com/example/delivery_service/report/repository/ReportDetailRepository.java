package com.example.delivery_service.report.repository;

import com.example.delivery_service.report.model.ReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, Long> {
}