package com.manufacturing.service.impl;

import com.manufacturing.dao.CarDao;
import com.manufacturing.dao.DriverDao;
import com.manufacturing.lib.Inject;
import com.manufacturing.lib.Service;
import com.manufacturing.model.Car;
import com.manufacturing.model.Driver;
import com.manufacturing.service.CarService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;
    @Inject
    private DriverDao driverDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).orElseThrow();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.deleteById(id);
    }

    @Override
    public boolean delete(Car car) {
        return carDao.delete(car);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        if (!driverDao.getAll().contains(driver)) {
            driverDao.create(driver);
        }
        car.getDrivers().add(driver);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        carDao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return carDao.getAll().stream()
                .filter(c -> c.getDrivers()
                        .stream()
                        .map(d -> d.getId())
                        .collect(Collectors.toList())
                        .contains(driverId))
                .collect(Collectors.toList());
    }
}
