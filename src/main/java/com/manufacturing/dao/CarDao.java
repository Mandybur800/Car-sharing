package com.manufacturing.dao;

import com.manufacturing.model.Car;
import java.util.List;

public interface CarDao extends GenericDao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}
