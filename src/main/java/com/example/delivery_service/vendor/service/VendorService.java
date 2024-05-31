package com.example.delivery_service.vendor.service;

import com.example.delivery_service.core.exception.exceptions.VendorNotFoundException;
import com.example.delivery_service.vendor.dto.*;
import com.example.delivery_service.vendor.model.Vendor;
import com.example.delivery_service.vendor.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.example.delivery_service.vendor.dto.VendorMapper.*;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VendorService implements VendorServiceInterface {
    private final VendorRepository repository;

    @Override
    @Transactional
    public VendorDto save(VendorDto dto) {
        return toDtoFromVendor(repository.save(toVendorFromDto(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<VendorDto> findAll() {
        return repository.findAll()
            .stream()
            .map(VendorMapper::toDtoFromVendor)
            .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VendorDto findById(Long id) {
        return toDtoFromVendor(getExistingVendorById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getExistingVendorById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public VendorDto update(Long id, VendorDto dto) {
        Vendor vendor = getExistingVendorById(id);
        updateName(dto.getName(), vendor);

        return toDtoFromVendor(repository.save(vendor));
    }

    public Vendor getExistingVendorById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new VendorNotFoundException("Поставщик с id " + id + " не найден.")
        );
    }

    private void updateName(String name, Vendor vendor) {
        if (name != null && !name.isBlank()) {
            vendor.setName(name);
        }
    }
}