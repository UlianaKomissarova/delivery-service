package com.example.delivery_service.core.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
        title = "Delivery Service Api",
        description = "Interface of receiving deliveries from vendors", version = "1.0.0",
        contact = @Contact(
            name = "Komissarova Uliana",
            email = "ulyana_vas@mail.ru",
            url = "https://github.com/UlianaKomissarova/delivery-service"
        )
    )
)
public class DeliveryApiConfig {
}