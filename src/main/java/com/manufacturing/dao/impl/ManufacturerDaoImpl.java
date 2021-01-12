package com.manufacturing.dao.impl;

import com.manufacturing.dao.ManufacturerDao;
import com.manufacturing.db.Storage;
import com.manufacturing.lib.Dao;
import com.manufacturing.model.Manufacturer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long manufacturerId) {
        return getAll().stream()
                .filter(m -> Objects.equals(m.getId(), manufacturerId))
                .findFirst();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturerUpdate) {
        Manufacturer manufacturer = getAll().stream()
                .filter(m -> Objects.equals(m.getId(), manufacturerUpdate.getId()))
                .findFirst().get();
        int indexOf = Storage.manufacturers.indexOf(manufacturer);
        return Storage.manufacturers.set(indexOf, manufacturerUpdate);
    }

    @Override
    public boolean deleteById(Long manufacturerId) {
        return Storage.manufacturers.removeIf(m -> Objects.equals(m.getId(), manufacturerId));
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return Storage.manufacturers.remove(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
