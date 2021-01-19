package com.manufacturing.service;

import com.manufacturing.model.Manufacturer;
import java.util.List;

public interface ManufacturerService extends GenericService<Manufacturer, Long> {
    @Override
    Manufacturer create(Manufacturer manufacturer);

    @Override
    Manufacturer get(Long manufacturerId);

    @Override
    Manufacturer update(Manufacturer manufacturer);

    @Override
    boolean delete(Long manufacturerId);

    @Override
    List<Manufacturer> getAll();
}
