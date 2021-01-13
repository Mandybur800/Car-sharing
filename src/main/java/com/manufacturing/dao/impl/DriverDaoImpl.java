package com.manufacturing.dao.impl;

import com.manufacturing.dao.DriverDao;
import com.manufacturing.db.Storage;
import com.manufacturing.lib.Dao;
import com.manufacturing.model.Driver;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long driverId) {
        return getAll().stream()
                .filter(d -> Objects.equals(d.getId(), driverId))
                .findFirst();
    }

    @Override
    public Driver update(Driver driverUpdate) {
        Driver driver = get(driverUpdate.getId()).get();
        int indexOf = Storage.drivers.indexOf(driver);
        return Storage.drivers.set(indexOf, driverUpdate);
    }

    @Override
    public boolean deleteById(Long driverId) {
        return Storage.drivers.removeIf(d -> Objects.equals(d.getId(), driverId));
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }
}
