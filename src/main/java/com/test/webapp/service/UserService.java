package com.test.webapp.service;

import com.test.webapp.entity.User;

public interface UserService {

    public User findByName(String name);
}
