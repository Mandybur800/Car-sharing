package com.manufacturing.service.impl;

import com.manufacturing.dao.DriverDao;
import com.manufacturing.lib.Inject;
import com.manufacturing.lib.Service;
import com.manufacturing.model.Driver;
import com.manufacturing.service.DriverService;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        return driverDao.get(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.deleteById(id);
    }
}
