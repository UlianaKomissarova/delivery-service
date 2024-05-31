package com.example.delivery_service.core.exception.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Schema(description = "Сущность ошибки")
public class ExceptionResponse {
    private final String error;
}
