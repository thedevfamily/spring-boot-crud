package com.thedevfamily.usermanagement.service;


import com.mongodb.MongoException;
import com.thedevfamily.usermanagement.model.User;
import com.thedevfamily.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User userDetails) {
        User user = new User(userDetails.getUsername(), userDetails.getFirstName(),userDetails.getLastName(),userDetails.getEmail());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> deleteUser(String id) {
        Optional<User> fetchedUser =  userRepository.findById(id);
        if(fetchedUser.isPresent()) userRepository.deleteById(id);
        return fetchedUser;
    }

    @Override
    public User updateUser(User userDetails, String id) {
        if(userRepository.findById(id).isPresent()) {
            User existingUser = userRepository.findById(id).get();
            if (userDetails.getFirstName() != null)
                existingUser.setFirstName(userDetails.getFirstName());
            if (userDetails.getLastName() != null)
                existingUser.setLastName(userDetails.getLastName());
            if (userDetails.getUsername() != null)
                existingUser.setUsername(userDetails.getUsername());
            if (userDetails.getEmail() != null)
                existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        } else
            throw new MongoException("Record not found");
    }
}
