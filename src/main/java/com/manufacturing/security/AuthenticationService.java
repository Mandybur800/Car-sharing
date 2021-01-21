package com.manufacturing.security;

import com.manufacturing.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
