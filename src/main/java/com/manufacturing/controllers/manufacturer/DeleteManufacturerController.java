package com.manufacturing.controllers.manufacturer;

import com.manufacturing.lib.Injector;
import com.manufacturing.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.manufacturing");
    private final ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String manufacturerId = req.getParameter("id");
        Long id = Long.parseLong(manufacturerId);
        manufacturerService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/manufacturer/all");
    }
}
