package com.example.delivery_service.vendor.service;

import com.example.delivery_service.vendor.dto.VendorDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface VendorServiceInterface {
    VendorDto save(VendorDto dto);

    Collection<VendorDto> findAll();

    VendorDto findById(@PathVariable Long id);

    void delete(@PathVariable Long id);

    VendorDto update(Long id, VendorDto dto);
}