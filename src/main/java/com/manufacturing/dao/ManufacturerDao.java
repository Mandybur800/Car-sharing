package com.manufacturing.dao;

import com.manufacturing.model.Manufacturer;
import java.util.List;
import java.util.Optional;

public interface ManufacturerDao extends GenericDao<Manufacturer, Long> {
    @Override
    Manufacturer create(Manufacturer manufacturer);

    @Override
    Optional<Manufacturer> get(Long manufacturerId);

    @Override
    Manufacturer update(Manufacturer manufacturer);

    @Override
    boolean delete(Long manufacturerId);

    @Override
    List<Manufacturer> getAll();
}
