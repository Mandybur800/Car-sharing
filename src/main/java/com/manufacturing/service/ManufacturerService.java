package com.manufacturing.service;

import com.manufacturing.model.Manufacturer;
import java.util.List;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long manufacturerId);

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long manufacturerId);

    boolean delete(Manufacturer manufacturer);

    List<Manufacturer> getAllManufacturers();
}
