package com.manufacturing.dao;

import com.manufacturing.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao {
    Car create(Car car);

    Optional<Car> get(Long carId);

    Car update(Car car);

    boolean deleteById(Long carId);

    boolean delete(Car car);

    List<Car> getAll();

    List<Car> getAllByDriver(Long driverId);
}
