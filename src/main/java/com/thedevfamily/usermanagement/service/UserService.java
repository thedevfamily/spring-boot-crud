package com.thedevfamily.usermanagement.service;

import com.thedevfamily.usermanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User userDetails);
    List<User> getAllUsers();
    Optional<User> getUser(String id);
    Optional<User> deleteUser(String id);
    User updateUser(User userDetails, String id);
}
