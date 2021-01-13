package com.manufacturing;

import com.manufacturing.lib.Injector;
import com.manufacturing.model.Car;
import com.manufacturing.model.Driver;
import com.manufacturing.model.Manufacturer;
import com.manufacturing.service.CarService;
import com.manufacturing.service.DriverService;
import com.manufacturing.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.manufacturing");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer1 = new Manufacturer("Volkswagen", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Audi", "Germany");
        Manufacturer manufacturer3 = new Manufacturer("BMV", "Germany");
        Manufacturer manufacturer4 = new Manufacturer("Tesla", "USA");
        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        manufacturerService.create(manufacturer4);
        System.out.println(manufacturerService.getAll());
        Manufacturer updatedManufacturer = manufacturerService.get(2L);
        updatedManufacturer.setName("Audiens");
        manufacturerService.update(updatedManufacturer);
        System.out.println(manufacturerService.getAll());
        manufacturerService.deleteById(1L);
        System.out.println(manufacturerService.get(2L));
        System.out.println(manufacturerService.getAll());

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driver1 = new Driver("Bob", "62478434");
        Driver driver2 = new Driver("Alice", "62474738");
        Driver driver3 = new Driver("John", "62475385");
        Driver driver4 = new Driver("Kate", "58358863");
        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        driverService.create(driver4);
        driverService.delete(2L);
        System.out.println(driverService.getAll());
        Driver updatedDriver = driverService.get(0L);
        updatedDriver.setName("Bohdan");
        driverService.update(updatedDriver);
        System.out.println(driverService.getAll());

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car1 = new Car("i8", manufacturerService.get(3L));
        Car car2 = new Car("V500", manufacturerService.get(4L));
        Car car3 = new Car("S6", manufacturerService.get(4L));
        Car car4 = new Car("Verdon", manufacturerService.get(3L));
        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        carService.create(car4);
        carService.addDriverToCar(driverService.get(1L), carService.get(1L));
        carService.addDriverToCar(driverService.get(3L), carService.get(1L));
        carService.addDriverToCar(driverService.get(3L), carService.get(2L));
        System.out.println(carService.getAll());
        Car updatedCar = carService.get(1L);
        updatedCar.setModel("V800");
        carService.update(updatedCar);
        carService.delete(0L);
        System.out.println(carService.getAll());
        carService.removeDriverFromCar(driverService.get(1L), carService.get(1L));
        System.out.println(carService.getAllByDriver(3L));
        System.out.println(carService.getAll());
    }
}
