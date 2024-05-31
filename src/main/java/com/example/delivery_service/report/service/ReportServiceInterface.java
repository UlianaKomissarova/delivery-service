package com.example.delivery_service.report.service;

import com.example.delivery_service.report.model.Report;

import java.time.LocalDateTime;

public interface ReportServiceInterface {
    Report save(LocalDateTime startDate, LocalDateTime endDate);
}