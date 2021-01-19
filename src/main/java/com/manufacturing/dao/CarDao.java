package com.manufacturing.dao;

import com.manufacturing.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao extends GenericDao<Car, Long> {
    @Override
    Car create(Car car);

    @Override
    Optional<Car> get(Long carId);

    @Override
    Car update(Car car);

    @Override
    boolean delete(Long carId);

    @Override
    List<Car> getAll();

    List<Car> getAllByDriver(Long driverId);
}
