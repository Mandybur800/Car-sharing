package com.manufacturing.dao;

import com.manufacturing.model.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverDao extends GenericDao<Driver, Long> {
    @Override
    Driver create(Driver driver);

    @Override
    Optional<Driver> get(Long driverId);

    @Override
    Driver update(Driver driver);

    @Override
    boolean delete(Long driverId);

    @Override
    List<Driver> getAll();
}
