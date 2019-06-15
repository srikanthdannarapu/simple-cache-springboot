package com.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loader.Loader;
import com.model.Users;

@RestController
public class UsersResource {

    @Autowired
    Loader loader;

    @GetMapping(value = "/users")
    public List<Users> getUser() {
        return (List<Users>) loader.loadAndCacheAllUsers();
    }
}