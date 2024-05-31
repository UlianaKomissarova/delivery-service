package com.example.delivery_service.report.controller;

import com.example.delivery_service.report.model.Report;
import com.example.delivery_service.report.service.ReportServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Отчеты", description="Управление отчетами о поступлениях продукции по поставщикам")
public class ReportController {
    private final ReportServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Создание отчета",
        description = "За выбранный период показывает поступление видов продукции по поставщикам с итогами по весу и стоимости."
    )
    public Report save(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        log.info("Создание отчет по поставкам с " + startDate + " по " + endDate);

        return service.save(startDate, endDate);
    }
}