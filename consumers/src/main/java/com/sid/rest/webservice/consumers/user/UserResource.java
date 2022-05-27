package com.sid.rest.webservice.consumers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @GetMapping("users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        User user = userService.findUser(id);
        if (user == null)
            throw new UserNotFoundException("User is not found");
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo
                (WebMvcLinkBuilder.methodOn(this.getClass()).listofUsers());
        model.add(builder.withRel("all-users"));
        return model;
    }

    @PostMapping("users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userService.deleteUserById(id);
        if (user == null)
            throw new UserNotFoundException("User is not found");
    }
}
