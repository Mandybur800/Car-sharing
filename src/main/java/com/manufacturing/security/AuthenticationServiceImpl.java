package com.manufacturing.security;

import com.manufacturing.lib.Inject;
import com.manufacturing.lib.Service;
import com.manufacturing.model.Driver;
import com.manufacturing.service.DriverService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverByLogin = driverService.findByLogin(login);
        if (driverByLogin.isPresent() && driverByLogin.get().getPassword().equals(password)) {
            return driverByLogin.get();
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
