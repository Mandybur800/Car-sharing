package com.manufacturing.controllers.driver;

import com.manufacturing.lib.Injector;
import com.manufacturing.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.manufacturing");
    private static final String DRIVER_ID = "driver_id";
    private final DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        driverService.delete(id);
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (id.equals(driverId)) {
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/drivers/");
    }
}
