package com.manufacturing.service;

import com.manufacturing.model.Driver;
import java.util.List;

public interface DriverService extends GenericService<Driver, Long> {
    @Override
    Driver create(Driver driver);

    @Override
    Driver get(Long id);

    @Override
    List<Driver> getAll();

    @Override
    Driver update(Driver driver);

    @Override
    boolean delete(Long id);
}
