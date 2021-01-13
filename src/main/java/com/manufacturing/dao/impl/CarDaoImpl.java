package com.manufacturing.dao.impl;

import com.manufacturing.dao.CarDao;
import com.manufacturing.db.Storage;
import com.manufacturing.lib.Dao;
import com.manufacturing.model.Car;
import com.manufacturing.model.Driver;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long carId) {
        return getAll().stream()
                .filter(c -> Objects.equals(c.getId(), carId))
                .findFirst();
    }

    @Override
    public Car update(Car carUpdate) {
        Car car = get(carUpdate.getId()).get();
        int indexOf = Storage.cars.indexOf(car);
        return Storage.cars.set(indexOf, carUpdate);
    }

    @Override
    public boolean deleteById(Long carId) {
        return Storage.cars.removeIf(c -> Objects.equals(c.getId(), carId));
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return getAll().stream()
                .filter(c -> c.getDrivers()
                        .stream()
                        .map(Driver::getId)
                        .collect(Collectors.toList())
                        .contains(driverId))
                .collect(Collectors.toList());
    }
}
