package com.manufacturing;

import com.manufacturing.lib.Injector;
import com.manufacturing.model.Manufacturer;
import com.manufacturing.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.manufacturing");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer1 = new Manufacturer("Volkswagen", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Audi", "Germany");
        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        System.out.println(manufacturerService.getAll());
        Manufacturer updatedManufacturer = manufacturerService.get(1L);
        updatedManufacturer.setName("Audiens");
        manufacturerService.update(updatedManufacturer);
        System.out.println(manufacturerService.getAll());
        manufacturerService.deleteById(0L);
        System.out.println(manufacturerService.get(1L));
        manufacturerService.delete(updatedManufacturer);
        System.out.println(manufacturerService.getAll());
    }
}
