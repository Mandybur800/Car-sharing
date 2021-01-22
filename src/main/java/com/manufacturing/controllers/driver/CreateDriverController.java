package com.manufacturing.controllers.driver;

import com.manufacturing.lib.Injector;
import com.manufacturing.model.Driver;
import com.manufacturing.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.manufacturing");
    private final DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/driver/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("name");
        String license = req.getParameter("license");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordRepeat = req.getParameter("pwd-repeat");
        if (password.equals(passwordRepeat)) {
            Driver driver = new Driver(name, license, login, password);
            driverService.create(driver);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "passwords should be equals");
            req.setAttribute("log", login);
            req.getRequestDispatcher("/WEB-INF/views/driver/create.jsp").forward(req, resp);
        }
    }
}
