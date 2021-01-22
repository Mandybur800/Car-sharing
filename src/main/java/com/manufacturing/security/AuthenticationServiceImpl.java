package com.manufacturing.security;

import com.manufacturing.lib.Inject;
import com.manufacturing.lib.Service;
import com.manufacturing.model.Driver;
import com.manufacturing.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driverByLogin = driverService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));
        if (driverByLogin.getPassword().equals(password)) {
            return driverByLogin;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
