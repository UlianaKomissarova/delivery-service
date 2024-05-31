package com.example.delivery_service.vendor.dto;

import com.example.delivery_service.delivery.model.Delivery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Сущность поставщика")
public class VendorDto {
    private Long id;
    @NotBlank
    private String name;
    private List<Delivery> deliveries;
}