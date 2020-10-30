package com.thedevfamily.usermanagement.controller;

import com.thedevfamily.usermanagement.model.User;
import com.thedevfamily.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "user/api/v1/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User userDetails){
        User user = new User(userDetails.getUsername(), userDetails.getFirstName(),userDetails.getLastName(),userDetails.getEmail());
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") String id) {
        Optional<User> getUser = userService.getUser(id);
        return new ResponseEntity<Optional<User>>(getUser, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> deleteUser(@PathVariable(value = "id") String id) {
        Optional<User> getUser = userService.deleteUser(id);
        return new ResponseEntity<Optional<User>>(getUser, HttpStatus.OK);
    }


    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody User usedDetails, @PathVariable(value = "id") String id ){
        return new ResponseEntity<>(userService.updateUser(usedDetails, id), HttpStatus.OK);
    }
}
