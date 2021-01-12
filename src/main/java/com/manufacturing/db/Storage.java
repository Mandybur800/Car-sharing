package com.manufacturing.db;

import com.manufacturing.model.Car;
import com.manufacturing.model.Driver;
import com.manufacturing.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    private static long manufacturerId = 0;
    private static long driverId = 0;
    private static long carId = 0;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(manufacturerId++);
        manufacturers.add(manufacturer);
    }

    public static void addDriver(Driver driver) {
        driver.setId(driverId++);
        drivers.add(driver);
    }

    public static void addCar(Car car) {
        car.setId(carId++);
        cars.add(car);
    }
}
