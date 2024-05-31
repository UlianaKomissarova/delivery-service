package com.example.delivery_service.delivery.dto;

import com.example.delivery_service.delivery_position.dto.PositionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Сущность поставки")
public class DeliveryDto {
    @Positive
    private Long vendorId;
    private LocalDateTime arrivalTime;
    private List<PositionDto> positions;
}