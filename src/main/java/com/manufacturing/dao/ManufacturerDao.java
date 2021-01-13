package com.manufacturing.dao;

import com.manufacturing.model.Manufacturer;
import java.util.List;
import java.util.Optional;

public interface ManufacturerDao {
    Manufacturer create(Manufacturer manufacturer);

    Optional<Manufacturer> get(Long manufacturerId);

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long manufacturerId);

    List<Manufacturer> getAll();
}
