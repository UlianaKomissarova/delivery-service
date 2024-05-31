package com.example.delivery_service.vendor.dto;

import com.example.delivery_service.vendor.model.Vendor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class VendorMapper {
    public static Vendor toVendorFromDto(VendorDto dto) {
        return Vendor.builder()
            .name(dto.getName())
            .id(dto.getId())
            .deliveries(new ArrayList<>())
            .build();
    }

    public static VendorDto toDtoFromVendor(Vendor vendor) {
        return VendorDto.builder()
            .name(vendor.getName())
            .deliveries(vendor.getDeliveries())
            .id(vendor.getId())
            .build();
    }
}