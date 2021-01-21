package com.manufacturing.controllers.car;

import com.manufacturing.lib.Injector;
import com.manufacturing.model.Car;
import com.manufacturing.service.CarService;
import com.manufacturing.service.ManufacturerService;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.manufacturing");
    private final CarService carService = (CarService)
            injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/car/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            String model = req.getParameter("model");
            Long manufacturerId = Long.parseLong(req.getParameter("manufacturer"));
            Car car = new Car(model, manufacturerService.get(manufacturerId));
            carService.create(car);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (NoSuchElementException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/incorrect.jsp").forward(req, resp);
        }
    }
}
