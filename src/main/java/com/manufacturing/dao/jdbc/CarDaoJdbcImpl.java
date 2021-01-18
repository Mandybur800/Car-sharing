package com.manufacturing.dao.jdbc;

import com.manufacturing.dao.CarDao;
import com.manufacturing.lib.Dao;
import com.manufacturing.model.Car;
import com.manufacturing.model.Driver;
import com.manufacturing.model.Manufacturer;
import com.manufacturing.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (manufacturer_id, model) VALUE (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, car.getManufacturer().getId());
            statement.setString(2, car.getModel());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("This data can't be added to table\n"
                    + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Car> get(Long carId) {
        String query = "SELECT c.car_id, model, c.manufacturer_id,"
                + " manufacturer_name, origin "
                + "FROM cars c "
                + "INNER JOIN manufacturers m "
                + "ON m.manufacturer_id = c.manufacturer_id "
                + "WHERE c.car_id = ? AND c.deleted = FALSE;";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = getDrivers(carId, connection);
            if (resultSet.next()) {
                car = getCar(resultSet, connection);
                car.setDrivers(drivers);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("We can't get car with ID:" + carId, e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public Car update(Car car) {
        String insertQuery = "INSERT INTO cars_drivers (driver_id, car_id) VALUES (?, ?)";
        String query = "UPDATE cars SET manufacturer_id = ?, model = ? "
                + "WHERE car_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            statement.setLong(1, car.getManufacturer().getId());
            statement.setString(2, car.getModel());
            statement.setLong(3, car.getId());
            insertStatement.setLong(2, car.getId());
            statement.executeUpdate();
            deleteDriversByID(car.getId());
            List<Driver> drivers = car.getDrivers();
            for (Driver driver : drivers) {
                insertStatement.setLong(1, driver.getId());
                insertStatement.executeUpdate();
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("We can't update car with id:"
                    + car.getId(), e);
        }
    }

    @Override
    public boolean delete(Long carId) {
        String query = "UPDATE cars SET deleted = true WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Delete of car with id="
                    + carId + " is failed", e);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT car_id, c.manufacturer_id, model, manufacturer_name, origin "
                + "FROM cars c "
                + "INNER JOIN manufacturers m ON c.manufacturer_id = m.manufacturer_id "
                + "WHERE c.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(getCar(resultSet, connection));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Something going wrong"
                    + " with getting all cars", e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT cd.car_id, model, c.manufacturer_id, manufacturer_name, origin "
                + "FROM cars_drivers cd "
                + "INNER JOIN cars c ON cd.car_id = c.car_id "
                + "INNER JOIN manufacturers m ON c.manufacturer_id = m.manufacturer_id "
                + "INNER JOIN drivers d ON d.driver_id = cd.driver_id "
                + "WHERE cd.driver_id = ? AND d.deleted = FALSE AND c.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(getCar(resultSet, connection));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Something going wrong"
                    + " with getting all cars for driver:" + driverId, e);
        }
    }

    private void deleteDriversByID(Long carId) {
        String query = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("We can't delete drivers from list", e);
        }
    }

    private List<Driver> getDrivers(Long carId, Connection connection) {
        String query = "SELECT cd.driver_id, name, license_number "
                + "FROM cars_drivers cd "
                + "INNER JOIN drivers d "
                + "\tON d.driver_id = cd.driver_id "
                + "WHERE cd.car_id = ? AND d.deleted = FALSE";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(getDriver(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("We can't get drivers for this car", e);
        }
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Long driverId = resultSet.getObject("driver_id", Long.class);
        String name = resultSet.getString("name");
        String licenseNumber = resultSet.getString("license_number");
        Driver driver = new Driver(name, licenseNumber);
        driver.setId(driverId);
        return driver;
    }

    private Car getCar(ResultSet resultSet, Connection connection) throws SQLException {
        Long carId = resultSet.getObject("car_id", Long.class);
        String model = resultSet.getString("model");
        Manufacturer manufacturer = getManufacturer(resultSet);
        Car car = new Car(model, manufacturer);
        car.setId(carId);
        car.setDrivers(getDrivers(carId, connection));
        return car;
    }

    private Manufacturer getManufacturer(ResultSet resultSet) throws SQLException {
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String name = resultSet.getString("manufacturer_name");
        String country = resultSet.getString("origin");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(manufacturerId);
        return manufacturer;
    }
}
