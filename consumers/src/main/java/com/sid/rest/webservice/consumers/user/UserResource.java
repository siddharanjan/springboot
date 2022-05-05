package com.sid.rest.webservice.consumers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    //retrieveAll User
    //Get /users
    @GetMapping("/users")
    public List<User> listofUsers() {
        return userService.findAll();
    }


    //retrieveUser from id

}
