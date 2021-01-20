package com.manufacturing.controllers.car;

import com.manufacturing.lib.Injector;
import com.manufacturing.model.Car;
import com.manufacturing.service.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.manufacturing");
    private final CarService carService = (CarService)
            injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Car> allCars = carService.getAll();
        req.setAttribute("cars", allCars);
        req.getRequestDispatcher("/WEB-INF/views/car/all.jsp").forward(req, resp);
    }
}
