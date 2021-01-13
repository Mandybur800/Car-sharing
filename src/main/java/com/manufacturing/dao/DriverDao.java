package com.manufacturing.dao;

import com.manufacturing.model.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverDao {
    Driver create(Driver driver);

    Optional<Driver> get(Long driverId);

    Driver update(Driver driver);

    boolean deleteById(Long driverId);

    List<Driver> getAll();
}
