package com.manufacturing.service;

import com.manufacturing.model.Driver;
import java.util.List;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);

    boolean delete(Driver driver);
}