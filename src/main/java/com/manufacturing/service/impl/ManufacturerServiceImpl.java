package com.manufacturing.service.impl;

import com.manufacturing.dao.ManufacturerDao;
import com.manufacturing.lib.Inject;
import com.manufacturing.lib.Service;
import com.manufacturing.model.Manufacturer;
import com.manufacturing.service.ManufacturerService;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long manufacturerId) {
        return manufacturerDao.get(manufacturerId).get();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean deleteById(Long manufacturerId) {
        return manufacturerDao.deleteById(manufacturerId);
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return manufacturerDao.delete(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDao.getAllManufacturers();
    }
}
